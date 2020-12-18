package com.kodless.kotlinnews.service


import com.kodless.kotlinnews.model.NewsAPIJSON
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIService {

    private val BASE_URL = "https://newsapi.org/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NewsAPI::class.java)

    fun getData() : Observable<NewsAPIJSON>{
        return  api.getNews()
    }
}