package com.youssef.articles.presentation.features.articles

import androidx.fragment.app.viewModels
import com.youssef.articles.domain.models.Article
import com.youssef.articles.presentation.args.ArticleDetailsArgs
import com.youssef.articles.presentation.callback.OnItemClickListener
import com.youssef.articles.presentation.features.articles.adapter.ArticlesAdapter
import com.youssef.feature.articles.R
import com.youssef.feature.articles.databinding.FragmentArticlesBinding
import com.youssef.network.data.errors.getMessage
import com.youssef.network.data.errors.getType
import com.youssef.ui.extensions.hide
import com.youssef.ui.extensions.navigateSafe
import com.youssef.ui.extensions.show
import com.youssef.ui.presentation.BaseFragment
import com.youssef.utils.extensions.collect
import com.youssef.utils.states.DataState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesFragment : BaseFragment<FragmentArticlesBinding>(FragmentArticlesBinding::inflate) {

    private val viewModel by viewModels<ArticleViewModel>()

    @Inject
    lateinit var adapter: ArticlesAdapter

    override fun bindViews() {
        initUI()
        subscribeOnObservers()
    }

    private fun initUI() {
        setupRV()
    }

    private fun setupRV() {
        binding.articlesRV.adapter = adapter
        adapter.listen(object : OnItemClickListener<Article> {
            override fun onItemClicked(item: Article) {
                val args = ArticleDetailsArgs(item)
                navigateSafe(ArticlesFragmentDirections.toDetails(args))
            }
        })
    }

    private fun subscribeOnObservers() {
        collect(viewModel.articlesDataState) {
            when (it) {
                is DataState.Success -> {
                    hideLoading()
                    adapter.submitList(it.data)
                }

                is DataState.Failure -> {
                    hideLoading()
                    showMessage(it.throwable.getType().getMessage().text ?: "")
                }

                DataState.Loading -> showLoading()
            }
        }
    }

    private fun hideLoading() {
        binding.loadingLayout.loading.hide()
        binding.articlesRV.show()
    }

    private fun showLoading() {
        binding.loadingLayout.loading.show()
        binding.articlesRV.hide()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_articles
}
