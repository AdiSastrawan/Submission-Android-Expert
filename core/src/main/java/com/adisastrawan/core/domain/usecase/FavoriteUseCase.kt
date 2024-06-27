package com.adisastrawan.core.domain.usecase

import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {
    fun getAllFavorite(): Flow<Resource<List<Favorite>>>

}