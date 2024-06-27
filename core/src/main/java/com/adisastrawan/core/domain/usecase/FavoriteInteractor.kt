package com.adisastrawan.core.domain.usecase

import androidx.lifecycle.asLiveData
import com.adisastrawan.core.data.Resource
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteInteractor @Inject constructor(private val repository: INewsRepository):FavoriteUseCase {
    override fun getAllFavorite(): Flow<Resource<List<Favorite>>> {
        return repository.getAllFavorites()
    }
}