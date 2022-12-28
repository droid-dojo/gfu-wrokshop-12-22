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

    private const val Key = "knOufpxjluVRpKx3PAa9-z34D7F7rg8q4JzzRQHPMfo" //TODO: REPLACE
}