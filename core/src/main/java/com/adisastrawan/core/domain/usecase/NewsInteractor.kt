package com.adisastrawan.core.domain.usecase

import com.adisastrawan.core.data.NewsRepository
import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val repository: INewsRepository): NewsUseCase {
    override fun getAllNews(): Flow<Resource<List<News>>> {
        return repository.getAllNews()
    }
}