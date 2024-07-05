package com.adisastrawan.core.data

import android.util.Log
import com.adisastrawan.core.data.source.NetworkBoundResource
import com.adisastrawan.core.data.source.local.LocalDataSource
import com.adisastrawan.core.data.source.remote.RemoteDataSource
import com.adisastrawan.core.data.source.remote.network.ApiResponse
import com.adisastrawan.core.data.source.remote.response.NewsResponse
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.domain.repository.INewsRepository
import com.adisastrawan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class NewsRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : INewsRepository {
    private var lastQuery: String? = null
    override fun getAllNews(query: String?): Flow<Resource<List<News>>> {
        return object : NetworkBoundResource<List<News>, NewsResponse>() {
            override suspend fun saveCallResult(data: NewsResponse) {
                val newsList = DataMapper.mapResponseToEntities(data.articles)
                localDataSource.deleteAllNews()
                localDataSource.insertNews(newsList)
            }

            override suspend fun createCall(): Flow<ApiResponse<NewsResponse>> {
                return remoteDataSource.getAllNews(query)
            }

            override fun loadFromDB(): Flow<List<News>> {
                return localDataSource.getAllNews().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(dbSource: List<News>): Boolean {
                val isQuery = lastQuery != query
                lastQuery = query
                return dbSource.isEmpty() || isQuery
            }
        }.asFlow()
    }

    override fun getAllFavorites(): Flow<Resource<List<Favorite>>> = flow {
        emit(Resource.Loading())
        try {
            val data = localDataSource.getFavoriteNews().map {
                DataMapper.mapFavoriteEntitiesToDomain(it)
            }.first()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun isFavorite(id: String): Flow<Boolean> = flow {
        emit(localDataSource.isFavorite(id).first())
    }

    override suspend fun setFavorite(favorite: Favorite) {
        val isFavorite = isFavorite(favorite.id).first()
        if (isFavorite) {
            localDataSource.deleteFavorite(favorite.id)
        } else {
            localDataSource.insertFavorite(DataMapper.mapDomainToFavoriteEntities(favorite))
        }
    }
}