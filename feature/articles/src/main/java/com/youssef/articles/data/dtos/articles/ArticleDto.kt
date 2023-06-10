package com.youssef.articles.data.dtos.articles

import com.google.gson.annotations.SerializedName

class ArticleDto(
    @SerializedName("id") val id: Long,
    @SerializedName("abstract") val summary: String,
    @SerializedName("adx_keywords") val adxKeywords: String,
    @SerializedName("asset_id") val assetId: Long,
    @SerializedName("byline") val byline: String,
    @SerializedName("column") val column: String?,
    @SerializedName("des_facet") val desFacet: List<String>,
    @SerializedName("geo_facet") val geoFacet: List<String>,
    @SerializedName("title") val title: String,
    @SerializedName("type") val type: String,
    @SerializedName("updated") val updated: String,
    @SerializedName("url") val url: String?,
    @SerializedName("media") val media: List<MediaDto>?
)
