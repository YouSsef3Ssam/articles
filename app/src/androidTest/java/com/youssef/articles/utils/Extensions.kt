package com.youssef.articles.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers

fun withTag(tag: String): Matcher<View>? = ViewMatchers.withTagValue(Matchers.`is`(tag))

fun <VH : RecyclerView.ViewHolder> clickOnItem(
    recyclerViewTag: String,
    clickedItemTag: String,
    position: Int
) {
    Espresso.onView(withTag(recyclerViewTag))
        .perform(
            RecyclerViewActions.actionOnItemAtPosition<VH>(
                position,
                clickChildViewWithTag(clickedItemTag)
            )
        )
}

private fun clickChildViewWithTag(tag: String): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Click on a child view with specified id."
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewWithTag<View>(tag)
            v.performClick()
        }
    }
}
