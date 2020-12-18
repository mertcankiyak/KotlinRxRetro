package com.kodless.kotlinnews.service


import com.kodless.kotlinnews.model.NewsAPIJSON
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/top-headlines?country=us&apiKey=0f290cdcaef347b9b81783f315d62ac6")
    fun getNews(): Observable<NewsAPIJSON>
}