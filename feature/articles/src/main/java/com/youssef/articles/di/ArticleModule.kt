package com.youssef.articles.di

import com.youssef.articles.data.datasources.remote.ArticlesApi
import com.youssef.articles.data.repositories.ArticlesRepositoryImpl
import com.youssef.articles.domain.repositories.ArticlesRepository
import com.youssef.articles.domain.usecases.abstraction.ArticlesUseCase
import com.youssef.articles.domain.usecases.implementation.ArticlesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ArticleModule {

    @Provides
    @Singleton
    fun provideArticleService(retrofit: Retrofit): ArticlesApi =
        retrofit.create(ArticlesApi::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingArticleModule {

    @Binds
    @Singleton
    abstract fun bindArticleRepository(
        articlesRepositoryImpl: ArticlesRepositoryImpl
    ): ArticlesRepository

    @Binds
    @Singleton
    abstract fun bindArticleUseCase(
        articlesUseCaseImpl: ArticlesUseCaseImpl
    ): ArticlesUseCase
}
