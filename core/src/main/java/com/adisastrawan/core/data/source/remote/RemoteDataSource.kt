package com.adisastrawan.core.data.source.remote

import com.adisastrawan.core.data.source.remote.network.ApiResponse
import com.adisastrawan.core.data.source.remote.network.ApiService
import com.adisastrawan.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    fun getAllNews(): Flow<ApiResponse<NewsResponse>> {
        return flow {
            try {
                val response = apiService.getNews(query = "bitcoin")
                val dataArray = response.articles
                if (dataArray!!.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}