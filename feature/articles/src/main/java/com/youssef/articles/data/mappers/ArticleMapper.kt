package com.youssef.articles.data.mappers

import com.youssef.articles.data.dtos.articles.ArticleDto
import com.youssef.articles.domain.models.Article
import com.youssef.utils.BaseMapper
import javax.inject.Inject

class ArticleMapper @Inject constructor() : BaseMapper<ArticleDto, Article> {

    override fun map(data: ArticleDto): Article = with(data) {
        Article(
            id = id,
            title = title,
            summary = summary,
            url = url,
            tags = desFacet.joinToString(", "),
            poster = media?.firstOrNull()?.metadata?.firstOrNull()?.url,
            media = media?.map { media -> media.metadata.map { it.url } }?.flatten() ?: listOf()
        )
    }
}
