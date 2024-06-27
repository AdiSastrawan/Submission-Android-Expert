package com.adisastrawan.core.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val newsId: String,
    val url: String? =null,
    val description: String? = null,
    val publishedAt: String?=null,
    val title: String? = null,
    val image : String? =null
):Parcelable
