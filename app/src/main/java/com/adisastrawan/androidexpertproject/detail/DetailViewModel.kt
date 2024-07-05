package com.adisastrawan.androidexpertproject.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.usecase.DetailUseCase

class DetailViewModel(private val detailUseCase: DetailUseCase) : ViewModel() {
    fun isFavorite(id: String) = detailUseCase.isFavorite(id).asLiveData()
    suspend fun setFavorite(favorite: Favorite) = detailUseCase.setFavorite(favorite)
}