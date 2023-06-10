package com.youssef.articles.presentation.args

import android.os.Parcelable
import com.youssef.articles.domain.models.Article
import kotlinx.parcelize.Parcelize

@Parcelize
class ArticleDetailsArgs(val article: Article) : Parcelable
