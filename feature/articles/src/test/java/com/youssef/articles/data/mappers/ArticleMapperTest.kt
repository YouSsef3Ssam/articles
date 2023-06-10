package com.youssef.articles.data.mappers

import com.youssef.articles.data.dtos.articles.ArticleDto
import com.youssef.articles.data.utils.Mocks
import com.youssef.articles.domain.models.Article
import com.youssef.utils.BaseMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArticleMapperTest {
    private lateinit var mapper: BaseMapper<ArticleDto, Article>

    @Before
    fun setUp() {
        mapper = ArticleMapper()
    }

    @Test
    fun `test map`() {
        val articleDto = Mocks.articleDto
        val article = mapper.map(articleDto)
        assertEquals(Article::class.java, article.javaClass)
        assertEquals(articleDto.id, article.id)
        assertEquals(articleDto.title, article.title)
        assertEquals(articleDto.media?.firstOrNull()?.metadata?.firstOrNull()?.url, article.poster)
        assertEquals(
            articleDto.media?.map { media -> media.metadata.map { it.url } }?.flatten(),
            article.media
        )
        if (articleDto.desFacet.isNotEmpty()) {
            assertEquals(articleDto.desFacet.size.minus(1), article.tags.count { it == ',' })
        }
    }
}
