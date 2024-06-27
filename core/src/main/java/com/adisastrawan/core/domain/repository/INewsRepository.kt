package com.adisastrawan.core.domain.repository

import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getAllFavorites(): Flow<Resource<List<Favorite>>>
    fun isFavorite(id:String): Flow<Boolean>
    suspend fun setFavorite(favorite: Favorite)
}