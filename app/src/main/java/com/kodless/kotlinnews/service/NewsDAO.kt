package com.kodless.kotlinnews.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kodless.kotlinnews.model.Article

@Dao
interface NewsDAO {
    //Veritabanına ulaşmak istediğimiz metodları yazıyoruz.
    @Insert
    suspend fun insertAll(vararg haberler:Article): List<Long>

    @Query("SELECT * FROM Article")
    suspend fun getAllNews(): List<Article>

    @Query("SELECT * FROM Article WHERE uuid= :newsId")
    suspend fun getNews(newsId:Int): Article

    @Query("DELETE FROM Article")
    suspend fun deleteAllCountries()
}