package com.example.workshop.unsplash.api

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

object Logger : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("Foo", "Request nach: ${chain.request().url}")
        Log.d("Foo", "Headers: ${chain.request().headers}")
        return chain.proceed(chain.request())
    }

}