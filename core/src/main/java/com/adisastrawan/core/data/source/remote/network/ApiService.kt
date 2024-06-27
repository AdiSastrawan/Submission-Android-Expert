package com.adisastrawan.core.data.source.remote.network

import com.adisastrawan.core.BuildConfig
import com.adisastrawan.core.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

    @GET("top-headlines")
    fun getTopHeadlineNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse
}