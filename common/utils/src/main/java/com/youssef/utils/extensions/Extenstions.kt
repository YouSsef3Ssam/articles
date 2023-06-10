package com.youssef.utils.extensions

import com.youssef.common.utils.BuildConfig
import com.youssef.utils.Flavors

fun isProduction(): Boolean = BuildConfig.FLAVOR == Flavors.PRODUCTION.value
fun isStaging(): Boolean = BuildConfig.FLAVOR == Flavors.STAGING.value
