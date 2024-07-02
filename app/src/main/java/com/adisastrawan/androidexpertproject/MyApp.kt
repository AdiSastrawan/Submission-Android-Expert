package com.adisastrawan.androidexpertproject

import android.app.Application
import com.adisastrawan.androidexpertproject.di.useCaseModule
import com.adisastrawan.androidexpertproject.di.viewModelModule
import com.adisastrawan.core.di.databaseModule
import com.adisastrawan.core.di.networkModule
import com.adisastrawan.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


open class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@MyApp)
            modules(listOf(
                databaseModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            ))
        }
    }
}