package com.youssef.utils.extensions

import com.youssef.common.utils.BuildConfig
import com.youssef.utils.EspressoIdlingResource
import com.youssef.utils.Flavors
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchIdling(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    EspressoIdlingResource.increment()
    val job = this.launch(context, start, block)
    job.invokeOnCompletion {
        EspressoIdlingResource.decrement()
    }
    return job
}

fun isProduction(): Boolean = BuildConfig.FLAVOR == Flavors.PRODUCTION.value
fun isStaging(): Boolean = BuildConfig.FLAVOR == Flavors.STAGING.value
