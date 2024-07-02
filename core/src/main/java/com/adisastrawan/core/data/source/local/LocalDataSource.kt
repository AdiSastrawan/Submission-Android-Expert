package com.adisastrawan.core.data.source.local

import com.adisastrawan.core.data.source.local.entity.FavoriteEntity
import com.adisastrawan.core.data.source.local.entity.NewsEntity
import com.adisastrawan.core.data.source.local.room.FavoriteDao
import com.adisastrawan.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val newsDao: NewsDao, private val favoriteDao: FavoriteDao) {
    fun getAllNews(): Flow<List<NewsEntity>> = newsDao.getAllNews()
    suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

    suspend fun deleteAllNews() = newsDao.deleteAll()
    fun getFavoriteNews(): Flow<List<FavoriteEntity>> = favoriteDao.getAllFavorite()
    suspend fun insertFavorite(favoriteNews: FavoriteEntity) = favoriteDao.insertFavorite(favoriteNews)
    suspend fun deleteFavorite(id: String) = favoriteDao.deleteFavorite(id)
    fun isFavorite(id: String): Flow<Boolean> = favoriteDao.isFavorite(id)

}