package com.adisastrawan.core.domain.usecase

import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor(private val repository: INewsRepository) : FavoriteUseCase {
    override fun getAllFavorite(): Flow<Resource<List<Favorite>>> {
        return repository.getAllFavorites()
    }
}