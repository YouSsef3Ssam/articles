package com.youssef.articles.data.mappers

import com.youssef.articles.data.dtos.articles.ArticleDto
import com.youssef.articles.domain.models.Article
import com.youssef.utils.BaseMapper
import javax.inject.Inject

class ArticlesMapper @Inject constructor(private val articleMapper: ArticleMapper) :
    BaseMapper<List<ArticleDto>, List<Article>> {

    override fun map(data: List<ArticleDto>): List<Article> = data.map { articleMapper.map(it) }
}
