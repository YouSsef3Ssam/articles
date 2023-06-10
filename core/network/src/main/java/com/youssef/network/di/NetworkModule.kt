package com.youssef.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pluto.plugins.network.PlutoInterceptor
import com.youssef.core.network.BuildConfig
import com.youssef.network.utils.Network
import com.youssef.utils.config.EnvironmentConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        environmentConfig: EnvironmentConfig
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(environmentConfig.getBaseUrl())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        plutoInterceptor: PlutoInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(plutoInterceptor)
        }
        httpClientBuilder.addInterceptor(headerInterceptor)

        return httpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun providePlutoInterceptor(): PlutoInterceptor =
        PlutoInterceptor()

    @Provides
    @Singleton
    fun provideHeaderInterceptor(environmentConfig: EnvironmentConfig): Interceptor =
        Interceptor { chain ->
            val originalRequest = chain.request()
            val url: HttpUrl = originalRequest.url.newBuilder()
                .addQueryParameter(Network.Queries.API_KEY, environmentConfig.getApiKey())
                .build()
            val newRequest = originalRequest.newBuilder().url(url).build()
            chain.proceed(newRequest)
        }
}
