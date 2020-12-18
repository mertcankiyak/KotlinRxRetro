package com.kodless.kotlinnews.model

import com.google.gson.annotations.SerializedName

data class NewsAPIJSON(
    @SerializedName("articles")
    var articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)