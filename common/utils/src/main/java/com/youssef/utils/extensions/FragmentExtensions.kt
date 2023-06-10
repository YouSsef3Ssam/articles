package com.youssef.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.SharedFlow

fun <T> Fragment.collect(sharedFlow: SharedFlow<T>, block: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchIdling {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            sharedFlow.collect {
                block(it)
            }
        }
    }
}
