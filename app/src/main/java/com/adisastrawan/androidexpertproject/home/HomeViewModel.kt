package com.adisastrawan.androidexpertproject.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.domain.usecase.NewsUseCase

class HomeViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun getAllNews(query: String? = null): LiveData<Resource<List<News>>> {
        return newsUseCase.getAllNews(query).asLiveData()
    }
}