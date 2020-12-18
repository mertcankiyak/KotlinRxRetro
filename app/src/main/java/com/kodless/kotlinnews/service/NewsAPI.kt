package com.kodless.kotlinnews.service


import com.kodless.kotlinnews.model.NewsAPIJSON
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/top-headlines?country=us&apiKey=NEWS-API-KEY")
    fun getNews(): Observable<NewsAPIJSON>
}
