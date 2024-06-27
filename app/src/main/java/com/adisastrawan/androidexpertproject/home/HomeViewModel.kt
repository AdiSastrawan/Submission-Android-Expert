package com.adisastrawan.androidexpertproject.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adisastrawan.core.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.last
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCase: NewsUseCase):ViewModel() {
    fun getAllNews() = newsUseCase.getAllNews().asLiveData()
}