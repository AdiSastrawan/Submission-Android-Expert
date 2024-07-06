package com.adisastrawan.core.data.source.remote

import android.util.Log
import com.adisastrawan.core.data.source.remote.network.ApiResponse
import com.adisastrawan.core.data.source.remote.network.ApiService
import com.adisastrawan.core.data.source.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    fun getAllNews(query: String? = null): Flow<ApiResponse<NewsResponse>> {
        return flow {
            try {
                Log.d("RemoteDataSource", "test")
                val response = apiService.getNews(query = query ?: "bitcoin")
                Log.d("RemoteDataSource", "response:$response")
                val dataArray = response.articles
                if ((dataArray ?: return@flow).isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                Log.d("RemoteDataSource", e.toString())
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}