package com.adisastrawan.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adisastrawan.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(news: FavoriteEntity)

    @Query("SELECT EXISTS (SELECT * FROM favorite WHERE id = :newsId)")
    fun isFavorite(newsId: String): Flow<Boolean>

    @Query("DELETE FROM favorite WHERE id = :newsId")
    suspend fun deleteFavorite(newsId: String)

    @Query("DELETE FROM favorite")
    fun deleteAll()
}