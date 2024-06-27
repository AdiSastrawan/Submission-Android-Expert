package com.adisastrawan.androidexpertproject.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.domain.usecase.DetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailUseCase: DetailUseCase): ViewModel() {
    fun isFavorite(id:String) = detailUseCase.isFavorite(id).asLiveData()
    suspend fun setFavorite(favorite: Favorite) = detailUseCase.setFavorite(favorite)
}