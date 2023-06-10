package com.youssef.articles.data.datasources.remote

import com.youssef.articles.data.dtos.articles.ArticleDto
import com.youssef.articles.data.utils.Network
import com.youssef.articles.data.utils.Network.EndPoints
import com.youssef.network.data.entities.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticlesApi {

    @GET(EndPoints.ARTICLES)
    suspend fun getArticles(@Path(Network.Path.PERIOD) period: Int): ApiResponse<ArticleDto>
}
