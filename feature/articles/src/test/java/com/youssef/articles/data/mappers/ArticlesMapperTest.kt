package com.youssef.articles.data.mappers

import com.youssef.articles.data.dtos.articles.ArticleDto
import com.youssef.articles.data.utils.Mocks
import com.youssef.articles.domain.models.Article
import com.youssef.utils.BaseMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArticlesMapperTest {
    private lateinit var mapper: BaseMapper<List<ArticleDto>, List<Article>>

    @Before
    fun setUp() {
        mapper = ArticlesMapper(ArticleMapper())
    }

    @Test
    fun `test map`() {
        val articlesDto = listOf(Mocks.articleDto)
        val articles = mapper.map(articlesDto)
        assertEquals(articles.size, articles.size)
    }
}
