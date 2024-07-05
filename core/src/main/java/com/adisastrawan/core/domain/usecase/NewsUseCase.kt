package com.adisastrawan.core.domain.usecase

import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(query: String? = null): Flow<Resource<List<News>>>
}