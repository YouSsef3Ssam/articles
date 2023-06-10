package com.youssef.articles.data.repositories

import com.youssef.articles.data.datasources.remote.ArticlesApi
import com.youssef.articles.data.mappers.ArticlesMapper
import com.youssef.articles.domain.models.Articles
import com.youssef.articles.domain.repositories.ArticlesRepository
import com.youssef.network.di.IoDispatcher
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArticlesRepositoryImpl @Inject constructor(
    private val api: ArticlesApi,
    private val mapper: ArticlesMapper,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ArticlesRepository {

    override suspend fun getArticles(period: Int): Flow<Articles> = flow {
        val articlesDtos = api.getArticles(period).results
        emit(mapper.map(articlesDtos))
    }.flowOn(dispatcher)
}
