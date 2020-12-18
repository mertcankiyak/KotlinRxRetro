package com.kodless.kotlinnews.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Article(

    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String?="BOS",

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String?="BOS",

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: String,

    //HATA Veren Kısım.
    /*@ColumnInfo(name = "source")
    @SerializedName("source")
    val source: Source,*/

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    val url: String,

    @ColumnInfo(name = "urlToImage")
    @SerializedName("urlToImage")
    val urlToImage: String?="BOS"
){
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}