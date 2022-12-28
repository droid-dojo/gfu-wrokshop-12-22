package com.unsplashed.client.android.api

import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(private val key: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().header(
                "Authorization",
                "Client-ID $key"
            ).build()
        )
    }
}