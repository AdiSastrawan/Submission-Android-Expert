package com.adisastrawan.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adisastrawan.core.data.source.local.entity.FavoriteEntity
import com.adisastrawan.core.data.source.local.entity.NewsEntity

@Database(entities = [NewsEntity::class, FavoriteEntity::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun favoriteDao(): FavoriteDao

}