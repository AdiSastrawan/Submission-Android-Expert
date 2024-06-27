package com.adisastrawan.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adisastrawan.core.data.source.remote.network.ApiService
import com.adisastrawan.core.BuildConfig
import com.adisastrawan.core.data.source.local.room.FavoriteDao
import com.adisastrawan.core.data.source.local.room.NewsDao
import com.adisastrawan.core.data.source.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java, "News.db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }

    @Provides
    fun provideFavoriteDao(database: NewsDatabase):FavoriteDao{
        return database.favoriteDao()
    }
}