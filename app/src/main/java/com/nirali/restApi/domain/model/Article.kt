package com.nirali.restApi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)

data class Source(
    val id: String,
    val name: String
)
