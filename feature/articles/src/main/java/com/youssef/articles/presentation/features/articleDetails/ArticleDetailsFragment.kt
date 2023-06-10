package com.youssef.articles.presentation.features.articleDetails

import androidx.navigation.fragment.navArgs
import com.youssef.uitest.TestTags
import com.youssef.uitest.testTag
import com.youssef.feature.articles.R
import com.youssef.feature.articles.databinding.FragmentArticleDetailsBinding
import com.youssef.ui.extensions.loadImage
import com.youssef.ui.extensions.openLink
import com.youssef.ui.presentation.BaseFragment

class ArticleDetailsFragment :
    BaseFragment<FragmentArticleDetailsBinding>(FragmentArticleDetailsBinding::inflate) {

    private val args by navArgs<ArticleDetailsFragmentArgs>()

    override fun bindViews() {
        initUI()
    }

    private fun initUI() {
        setData()
        registerClicks()
        testTags()
    }

    private fun setData() {
        with(args.data.article) {
            binding.posterIV.loadImage(poster, null)
            binding.summary.text = summary
            binding.title.text = title
            binding.tags.text = tags
        }
    }

    private fun registerClicks() {
        binding.openArticleBtn.setOnClickListener {
            args.data.article.url?.let { url: String ->
                openLink(url)
            }
        }
    }

    private fun testTags() {
        binding.articleDetailsFragmentLayout.testTag(TestTags.ArticleDetailsFragment.LAYOUT)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_article_details
}
