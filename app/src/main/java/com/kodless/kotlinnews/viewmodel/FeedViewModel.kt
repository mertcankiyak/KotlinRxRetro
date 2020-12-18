package com.kodless.kotlinnews.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.kodless.kotlinnews.model.Article
import com.kodless.kotlinnews.model.NewsAPIJSON
import com.kodless.kotlinnews.service.NewsAPIService
import com.kodless.kotlinnews.service.NewsDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application) : BaseViewModel(application) {

    val haberlerim = MutableLiveData<NewsAPIJSON>()
    private val newsApiService = NewsAPIService()
        private val disposable = CompositeDisposable()
    var haberListem : List<Article>?=null

     fun getDataFromAPI(){
        disposable.add(
            newsApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<NewsAPIJSON>(){
                    override fun onNext(t: NewsAPIJSON) {
                        haberListem = t.articles
                    }

                    override fun onError(e: Throwable) {
                        println("Bir hata meydana geldi : "+e.localizedMessage)
                    }

                    override fun onComplete() {

                        println("Veri çekme işlemi tamamlandı!")
                        storeInSQLite(haberListem!!)
                    }
                })
        )
    }
    private fun getDataFromSQLite(){
        launch {
            var news= NewsDatabase(getApplication()).newsDao().getAllNews()
            var sqlHaberListem = NewsAPIJSON(articles = news,status = "ok",totalResults = 10)
            haberlerim.value = sqlHaberListem
            Toast.makeText(getApplication(),"SQL Lite'dan Geldi Veri",Toast.LENGTH_LONG).show()
        }
    }

    private fun storeInSQLite(list: List<Article>){
        launch {
            val dao = NewsDatabase(getApplication()).newsDao()
            dao.deleteAllCountries()
            val listLong = dao.insertAll(*list.toTypedArray()) // list -> indivudual
            var i = 0
            while(i<list.size){
                list[i].uuid = listLong[i].toInt()
                i = i+1
            }
            getDataFromSQLite()
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}