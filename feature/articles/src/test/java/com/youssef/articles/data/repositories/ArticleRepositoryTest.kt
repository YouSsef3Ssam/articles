package com.youssef.articles.data.repositories

import com.youssef.articles.data.datasources.remote.ArticlesApi
import com.youssef.articles.data.mappers.ArticleMapper
import com.youssef.articles.data.mappers.ArticlesMapper
import com.youssef.articles.data.utils.Mocks
import com.youssef.articles.domain.models.Articles
import com.youssef.articles.domain.repositories.ArticlesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkClass
import io.mockk.unmockkAll
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArticleRepositoryTest {
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: ArticlesRepository

    private val dataSource: ArticlesApi = mockkClass(ArticlesApi::class)
    private val mapper = ArticlesMapper(ArticleMapper())

    private val expectedSuccessResult = Mocks.articleResponse
    private val expectedFailureResult = Mocks.exception

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        repository = ArticlesRepositoryImpl(dataSource, mapper, testDispatcher)
    }

    @Test
    fun `test getArticles with success response then return success`() = runTest {
        coEvery { dataSource.getArticles(any()) } returns expectedSuccessResult

        val response = repository.getArticles(30)

        var success: Articles? = null
        var error: Throwable? = null
        response
            .catch { error = it }
            .collect { success = it }

        assertNotNull(success)
        assertNull(error)
        assertEquals(expectedSuccessResult.results.size, success!!.size)
        assertEquals(expectedSuccessResult.results.map { it.id }, success!!.map { it.id })
        coVerify(exactly = 1) { dataSource.getArticles(any()) }
    }

    @Test
    fun `test getArticles with failure response then return error`() = runTest {
        coEvery { dataSource.getArticles(any()) } throws expectedFailureResult

        val response = repository.getArticles(30)

        var success: Articles? = null
        var error: Throwable? = null
        response
            .catch { error = it }
            .collect { success = it }

        assertNotNull(error)
        assertNull(success)
        assertEquals(expectedFailureResult.message, error!!.message)
        coVerify(exactly = 1) { dataSource.getArticles(any()) }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}
