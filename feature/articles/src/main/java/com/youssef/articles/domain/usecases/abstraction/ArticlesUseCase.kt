package com.youssef.articles.domain.usecases.abstraction

import com.youssef.articles.domain.models.Articles
import kotlinx.coroutines.flow.Flow

interface ArticlesUseCase {

    suspend fun getArticles(period: Int): Flow<Articles>
}
