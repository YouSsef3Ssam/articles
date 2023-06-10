package com.youssef.utils.di

import com.youssef.utils.config.EnvironmentConfig
import com.youssef.utils.config.EnvironmentConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {

    @Binds
    @Singleton
    abstract fun bindEnvironmentConfig(
        environmentConfigImpl: EnvironmentConfigImpl
    ): EnvironmentConfig
}
