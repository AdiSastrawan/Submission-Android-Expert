package com.adisastrawan.favorite.di

import com.adisastrawan.favorite.home.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module  {
    viewModel { FavoriteViewModel(get()) }
}
