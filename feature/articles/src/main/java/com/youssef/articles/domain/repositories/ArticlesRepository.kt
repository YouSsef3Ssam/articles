package com.youssef.articles.domain.repositories

import com.youssef.articles.domain.models.Articles
import kotlinx.coroutines.flow.Flow

interface ArticlesRepository {

    suspend fun getArticles(period: Int): Flow<Articles>
}
