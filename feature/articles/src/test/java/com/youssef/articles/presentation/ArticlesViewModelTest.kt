package com.youssef.articles.presentation

import app.cash.turbine.test
import com.youssef.articles.data.utils.Mocks
import com.youssef.articles.domain.usecases.abstraction.ArticlesUseCase
import com.youssef.articles.presentation.features.articles.ArticleViewModel
import com.youssef.utils.states.DataState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArticlesViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    private var useCase: ArticlesUseCase = mockk()

    private val expectedSuccessResult = Mocks.articles
    private val expectedFailureResult = Mocks.exception

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `test getArticles with success response then return success`() {
        runTest {
            coEvery { useCase.getArticles(any()) } answers { flow { emit(expectedSuccessResult) } }
            val viewModel = ArticleViewModel(useCase)
            viewModel.articlesDataState.test {
                assertEquals(DataState.Loading, awaitItem())
                assertEquals(DataState.Success(Mocks.articles), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            coVerify(exactly = 1) { useCase.getArticles(any()) }
        }
    }

    @Test
    fun `test getArticles with failure response then return error`() {
        runTest {
            coEvery { useCase.getArticles(any()) } answers { flow { throw expectedFailureResult } }
            val viewModel = ArticleViewModel(useCase)
            viewModel.articlesDataState.test {
                assertEquals(DataState.Loading, awaitItem())
                assertEquals(DataState.Failure(expectedFailureResult), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            coVerify(exactly = 1) { useCase.getArticles(any()) }
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
