package com.youssef.articles.data.dtos.articles

import com.google.gson.annotations.SerializedName

class MediaMetadataDto(
    @SerializedName("format") val format: String,
    @SerializedName("height") val height: Int,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int
)
