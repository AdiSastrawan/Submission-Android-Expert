package com.adisastrawan.core.domain.usecase

import com.adisastrawan.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    fun isFavorite(id: String): Flow<Boolean>
    suspend fun setFavorite(favorite: Favorite)
}