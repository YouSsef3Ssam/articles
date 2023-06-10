package com.youssef.articles.presnentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.youssef.uitest.TestTags
import com.youssef.articles.BaseTest
import com.youssef.articles.presentation.features.articles.adapter.ArticleHolder
import com.youssef.articles.utils.clickOnItem
import com.youssef.articles.utils.withTag
import org.junit.Test

class ArticlesTest : BaseTest() {

    @Test
    override fun startTest() {
        Thread.sleep(2000)
        onView(withTag(TestTags.ArticlesFragment.LAYOUT)).check(matches(isDisplayed()))
        onView(withTag(TestTags.ArticlesFragment.RV)).check(matches(isDisplayed()))
        openDetails()
    }

    private fun openDetails() {
        clickOnItem<ArticleHolder>(
            TestTags.ArticlesFragment.RV,
            TestTags.ArticlesFragment.RV_ITEM,
            0
        )
        onView(withTag(TestTags.ArticleDetailsFragment.LAYOUT)).check(matches(isDisplayed()))
    }
}