package com.youssef.articles.domain

import com.youssef.articles.data.utils.Mocks
import com.youssef.articles.domain.models.Articles
import com.youssef.articles.domain.repositories.ArticlesRepository
import com.youssef.articles.domain.usecases.abstraction.ArticlesUseCase
import com.youssef.articles.domain.usecases.implementation.ArticlesUseCaseImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import io.mockk.unmockkAll
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleUseCaseTest {

    private lateinit var useCase: ArticlesUseCase

    private val repository: ArticlesRepository = mockkClass(ArticlesRepository::class)

    private val expectedSuccessResult = Mocks.articles
    private val expectedFailureResult = Mocks.exception

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = ArticlesUseCaseImpl(repository)
    }

    @Test
    fun `test getArticles with success response then return success`() = runTest {
        coEvery { repository.getArticles(any()) } answers { flow { emit(expectedSuccessResult) } }

        val response = useCase.getArticles(30)

        var success: Articles? = null
        var error: Throwable? = null
        response
            .catch { error = it }
            .collect { success = it }

        assertNotNull(success)
        assertNull(error)
        assertEquals(expectedSuccessResult.size, success!!.size)
        assertEquals(expectedSuccessResult.map { it.id }, success!!.map { it.id })
        coVerify(exactly = 1) { repository.getArticles(any()) }
    }

    @Test
    fun `test getArticles with failure response then return error`() = runTest {
        coEvery { repository.getArticles(any()) } answers { flow { throw expectedFailureResult } }
        val response = useCase.getArticles(30)

        var success: Articles? = null
        var error: Throwable? = null
        response
            .catch { error = it }
            .collect { success = it }

        assertNotNull(error)
        assertNull(success)
        assertEquals(expectedFailureResult.message, error!!.message)
        coVerify(exactly = 1) { repository.getArticles(any()) }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}
