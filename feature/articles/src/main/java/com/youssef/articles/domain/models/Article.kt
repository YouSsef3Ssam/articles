package com.youssef.articles.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

typealias Articles = List<Article>

@Parcelize
data class Article(
    val id: Long,
    val summary: String,
    val title: String,
    val url: String?,
    val tags: String,
    val poster: String?,
    val media: List<String>
) : Parcelable
