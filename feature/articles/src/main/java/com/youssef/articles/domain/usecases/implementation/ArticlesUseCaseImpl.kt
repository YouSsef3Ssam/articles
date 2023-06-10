package com.youssef.articles.domain.usecases.implementation

import com.youssef.articles.domain.models.Articles
import com.youssef.articles.domain.repositories.ArticlesRepository
import com.youssef.articles.domain.usecases.abstraction.ArticlesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ArticlesUseCaseImpl @Inject constructor(private val repository: ArticlesRepository) :
    ArticlesUseCase {

    override suspend fun getArticles(period: Int): Flow<Articles> = repository.getArticles(period)
}
