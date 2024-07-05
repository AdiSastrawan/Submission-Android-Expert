package com.adisastrawan.favorite.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adisastrawan.core.domain.usecase.FavoriteUseCase

class FavoriteViewModel(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    fun getAllNews() = favoriteUseCase.getAllFavorite().asLiveData()
}
