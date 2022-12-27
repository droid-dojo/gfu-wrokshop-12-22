package com.example.workshop.unsplash.api

import okhttp3.Interceptor
import okhttp3.Response

object ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().header(
                "Authorization",
                "Client-ID $Key"
            ).build()
        )
    }

    private const val Key = "" //TODO: REPLACE
}