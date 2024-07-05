package com.adisastrawan.core.di

import androidx.room.Room
import com.adisastrawan.core.BuildConfig
import com.adisastrawan.core.data.NewsRepository
import com.adisastrawan.core.data.source.local.LocalDataSource
import com.adisastrawan.core.data.source.local.room.NewsDatabase
import com.adisastrawan.core.data.source.remote.RemoteDataSource
import com.adisastrawan.core.data.source.remote.network.ApiService
import com.adisastrawan.core.domain.repository.INewsRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("androidexpert".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidContext(), NewsDatabase::class.java, "News.db")
            .fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "newsapi.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname,"sha256/DeRCK8msLPkgAWJ3UYy6o4P9xTB2mHNpFNw9x1BetQE=")
            .add(hostname,"sha256/0Bbh/jEZSKymTy3kTOhsmlHKBB32EDu1KojrP3YfV9c=")
            .add(hostname,"sha256/C5+lpZ7tcVwmwQIMcRtPbsQtWLABXhQzejna0wHFr8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
