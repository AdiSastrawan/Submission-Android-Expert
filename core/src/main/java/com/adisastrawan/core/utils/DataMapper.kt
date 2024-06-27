package com.adisastrawan.core.utils

import com.adisastrawan.core.data.source.local.entity.FavoriteEntity
import com.adisastrawan.core.data.source.local.entity.NewsEntity
import com.adisastrawan.core.data.source.remote.response.ArticlesItem
import com.adisastrawan.core.domain.model.Favorite
import com.adisastrawan.core.domain.model.News

object DataMapper {
    fun mapEntitiesToDomain(newsEntities: List<NewsEntity>): List<News> {
        return newsEntities.map {
            News(
                newsId = it.newsId,
                title = it.title,
                description = it.description,
                image = it.image,
                publishedAt = it.publishedAt,
                url = it.url
            )
        }
    }

    fun mapResponseToEntities(newsResponse: List<ArticlesItem?>?): List<NewsEntity> {
        return newsResponse?.map {
            NewsEntity(
                newsId = it!!.url,
                title = it.title,
                description = it.description,
                image = it.urlToImage,
                publishedAt = it.publishedAt,
                url = it.url,
            )
        }
            ?: emptyList()
    }

    fun mapFavoriteEntitiesToDomain(favoriteEntities: List<FavoriteEntity>): List<Favorite> {
        return favoriteEntities.map {
            Favorite(
                id = it.id,
                title = it.title,
                description = it.description,
                image = it.image,
                url = it.url,
            )
        }
    }

    fun mapDomainToFavoriteEntities(favorite: Favorite) = FavoriteEntity(
        id = favorite.id,
        title = favorite.title,
        description = favorite.description,
        image = favorite.image,
        url = favorite.url
    )
}