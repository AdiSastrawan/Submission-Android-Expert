package com.adisastrawan.core.domain.usecase

import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow

class DetailInteractor(private val repository: INewsRepository) : DetailUseCase {
    override fun isFavorite(id: String): Flow<Boolean> {
        return repository.isFavorite(id)
    }

    override suspend fun setFavorite(favorite: Favorite) {
        return repository.setFavorite(favorite)
    }
}