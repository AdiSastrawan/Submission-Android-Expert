package com.adisastrawan.favorite.di

import com.adisastrawan.core.domain.usecase.DetailInteractor
import com.adisastrawan.core.domain.usecase.DetailUseCase
import com.adisastrawan.core.domain.usecase.FavoriteInteractor
import com.adisastrawan.core.domain.usecase.FavoriteUseCase
import com.adisastrawan.core.domain.usecase.NewsInteractor
import com.adisastrawan.core.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideFavoriteUseCase(favoriteInteractor: FavoriteInteractor): FavoriteUseCase

}