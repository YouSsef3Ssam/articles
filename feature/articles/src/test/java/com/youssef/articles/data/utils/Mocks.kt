package com.youssef.articles.data.utils

import com.youssef.articles.data.dtos.articles.ArticleDto
import com.youssef.articles.data.dtos.articles.MediaDto
import com.youssef.articles.data.dtos.articles.MediaMetadataDto
import com.youssef.network.data.entities.ApiResponse

object Mocks {

    private const val articleImageUrl =
        "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"

    val articleDto = ArticleDto(
        id = 1L,
        summary = "Hello World!",
        adxKeywords = "",
        assetId = 2L,
        byline = "321",
        column = null,
        desFacet = listOf("1", "2", "3"),
        geoFacet = listOf(),
        title = "Article #1",
        type = "",
        updated = "",
        url = null,
        media = listOf(
            MediaDto(
                listOf(
                    MediaMetadataDto(
                        format = "",
                        height = 100,
                        width = 100,
                        url = articleImageUrl
                    )
                )
            )
        )
    )

    val articleResponse = ApiResponse(listOf(articleDto))

    val exception = Throwable("Can't get articles, please try again later.")
}
