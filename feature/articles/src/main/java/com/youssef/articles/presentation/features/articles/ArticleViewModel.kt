package com.youssef.articles.presentation.features.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youssef.articles.domain.models.Articles
import com.youssef.articles.domain.usecases.abstraction.ArticlesUseCase
import com.youssef.articles.presentation.utils.Constants
import com.youssef.utils.extensions.catchError
import com.youssef.utils.states.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ArticleViewModel @Inject constructor(private val useCase: ArticlesUseCase) : ViewModel() {

    private val _articlesDataState: MutableStateFlow<DataState<Articles>> =
        MutableStateFlow(DataState.Loading)
    val articlesDataState: StateFlow<DataState<Articles>> get() = _articlesDataState

    init {
        getArticles()
    }

    private fun getArticles() {
        viewModelScope.launch {
            useCase.getArticles(Constants.ARTICLE_PERIOD)
                .catchError { _articlesDataState.emit(DataState.Failure(it)) }
                .collect { _articlesDataState.emit(DataState.Success(it)) }
        }
    }
}
