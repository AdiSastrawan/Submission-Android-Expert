package com.adisastrawan.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image")
    val image: String
)
