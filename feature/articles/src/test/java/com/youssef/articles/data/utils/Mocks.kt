package com.youssef.articles.data.utils

import com.youssef.articles.data.dtos.articles.ArticleDto
import com.youssef.network.data.entities.ApiResponse

object Mocks {

    private val articleDto = ArticleDto(
        id = 1L,
        summary = "Hello World!",
        adxKeywords = "",
        assetId = 2L,
        byline = "321",
        column = null,
        desFacet = listOf(),
        geoFacet = listOf(),
        title = "Article #1",
        type = "",
        updated = "",
        url = null,
        media = null
    )

    val articleResponse = ApiResponse(listOf(articleDto))

    val exception = Throwable("Can't get articles, please try again later.")
}
