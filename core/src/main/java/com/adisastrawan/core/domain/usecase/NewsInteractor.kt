package com.adisastrawan.core.domain.usecase

import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.News
import com.adisastrawan.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class NewsInteractor(private val repository: INewsRepository) : NewsUseCase {
    override fun getAllNews(query: String?): Flow<Resource<List<News>>> {
        return repository.getAllNews(query)
    }
}