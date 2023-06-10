package com.youssef.articles.data.dtos.articles

import com.google.gson.annotations.SerializedName

class MediaDto(@SerializedName("media-metadata") val metadata: List<MediaMetadataDto>)
