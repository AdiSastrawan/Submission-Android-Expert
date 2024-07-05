package com.adisastrawan.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo(name = "newsId")
    val newsId: String,
    @ColumnInfo(name = "url")
    val url: String? = null,
    @ColumnInfo(name = "description")
    val description: String? = null,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: String? = null,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image")
    val image: String? = null
)
