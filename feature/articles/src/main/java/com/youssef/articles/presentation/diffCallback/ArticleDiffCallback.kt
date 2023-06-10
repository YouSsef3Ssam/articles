package com.youssef.articles.presentation.diffCallback

import androidx.recyclerview.widget.DiffUtil
import com.youssef.articles.domain.models.Article

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Article, newItem: Article) =
        oldItem == newItem
}
