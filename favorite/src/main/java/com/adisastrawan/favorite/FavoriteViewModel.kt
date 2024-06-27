package com.adisastrawan.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adisastrawan.core.domain.usecase.FavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class FavoriteViewModel @Inject constructor(private val favoriteUseCase: FavoriteUseCase) : ViewModel() {
    fun getAllNews() = favoriteUseCase.getAllFavorite().asLiveData()
}
