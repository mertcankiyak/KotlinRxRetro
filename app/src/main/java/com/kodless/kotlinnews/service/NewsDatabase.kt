package com.kodless.kotlinnews.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kodless.kotlinnews.model.Article

@Database(entities = arrayOf(Article::class), version = 1)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDAO


    //Singleton
    companion object {
       @Volatile private var instance: NewsDatabase?=null
        //Volatile eklememizin sebebi farklı threadlerde çalışması için

        private val lock = Any()

        //instance var mı yok mu kontrolünü yap
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, NewsDatabase::class.java, "newsdatabase"
        ).build()
    }
}