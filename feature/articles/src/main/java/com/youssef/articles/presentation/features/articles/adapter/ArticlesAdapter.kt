package com.youssef.articles.presentation.features.articles.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.youssef.articles.domain.models.Article
import com.youssef.articles.presentation.callback.OnItemClickListener
import com.youssef.articles.presentation.diffCallback.ArticleDiffCallback
import javax.inject.Inject

class ArticlesAdapter @Inject constructor() :
    ListAdapter<Article, ArticleHolder>(ArticleDiffCallback()) {

    private var onItemClickListener: OnItemClickListener<Article>? = null
    fun listen(onItemClickListener: OnItemClickListener<Article>) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder =
        ArticleHolder.from(parent, onItemClickListener)
}
