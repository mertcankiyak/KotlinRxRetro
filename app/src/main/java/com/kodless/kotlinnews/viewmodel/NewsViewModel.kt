package com.kodless.kotlinnews.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.kodless.kotlinnews.model.Article
import com.kodless.kotlinnews.service.NewsDatabase
import kotlinx.coroutines.launch

class NewsViewModel(application: Application): BaseViewModel(application) {

    val newsLiveData = MutableLiveData<Article>()

    fun getDataFromRoom(newsId:Int){
        launch {
            val dao = NewsDatabase(getApplication()).newsDao()
            val news= dao.getNews(newsId)
            newsLiveData.value = news
        }
    }

}