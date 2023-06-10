package com.youssef.utils.config

import com.youssef.common.utils.BuildConfig
import com.youssef.utils.extensions.isProduction
import com.youssef.utils.extensions.isStaging
import javax.inject.Inject

class EnvironmentConfigImpl @Inject constructor() : EnvironmentConfig {

    override fun getBaseUrl(): String = when {
        isProduction() -> BuildConfig.PRODUCTION_BASE_URL
        isStaging() -> BuildConfig.STAGING_BASE_URL
        else -> BuildConfig.STAGING_BASE_URL
    }

    override fun getApiKey(): String = when {
        isProduction() -> BuildConfig.PRODUCTION_API_KEY
        isStaging() -> BuildConfig.STAGING_API_KEY
        else -> BuildConfig.STAGING_API_KEY
    }
}
