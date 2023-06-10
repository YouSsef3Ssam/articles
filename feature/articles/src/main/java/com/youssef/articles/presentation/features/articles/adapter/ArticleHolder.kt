package com.youssef.articles.presentation.features.articles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.youssef.articles.domain.models.Article
import com.youssef.articles.presentation.callback.OnItemClickListener
import com.youssef.feature.articles.databinding.ItemArticleBinding
import com.youssef.ui.extensions.loadImage

class ArticleHolder(
    private val binding: ItemArticleBinding,
    private val onItemClicked: OnItemClickListener<Article>?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article) {
        binding.posterIV.loadImage(article.poster, null)
        binding.summaryTV.text = article.summary
        binding.root.setOnClickListener { onItemClicked?.onItemClicked(article) }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            onItemClicked: OnItemClickListener<Article>?
        ): ArticleHolder {
            val binding = ItemArticleBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ArticleHolder(binding, onItemClicked)
        }
    }
}
