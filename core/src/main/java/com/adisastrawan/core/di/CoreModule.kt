package com.adisastrawan.core.di

import androidx.room.Room
import com.adisastrawan.core.BuildConfig
import com.adisastrawan.core.data.NewsRepository
import com.adisastrawan.core.data.source.local.LocalDataSource
import com.adisastrawan.core.data.source.local.room.NewsDatabase
import com.adisastrawan.core.data.source.remote.RemoteDataSource
import com.adisastrawan.core.data.source.remote.network.ApiService
import com.adisastrawan.core.domain.repository.INewsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory {
        get<NewsDatabase>().newsDao()
    }
    factory {
        get<NewsDatabase>().favoriteDao()
    }
    single {
        Room.databaseBuilder(androidContext(), NewsDatabase::class.java, "News.db")
            .fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get(), get())
    }
    single {
        RemoteDataSource(get())
    }
    single<INewsRepository> {
        NewsRepository(get(), get())
    }
}
