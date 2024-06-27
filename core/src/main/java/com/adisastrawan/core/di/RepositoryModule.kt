package com.adisastrawan.core.di

import com.adisastrawan.core.data.NewsRepository
import com.adisastrawan.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(newsRepository: NewsRepository): INewsRepository

}