package com.unsplashed.client.android

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.unsplashed.client.UnsplashedClient
import com.unsplashed.client.android.api.ApiKeyInterceptor
import com.unsplashed.client.android.api.UnsplashApi
import com.unsplashed.client.model.Photo
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

class UnsplashedAndroidClient(
    apiKey: String
) : UnsplashedClient {

    private val okhttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(apiKey))
//        .addInterceptor(Logger)
        .build()


    private val json = Json { ignoreUnknownKeys = true }
    private val converter = json.asConverterFactory("application/json".toMediaType())

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com")
        .client(okhttpClient)
        .addConverterFactory(converter)
        .build()

    private val api: UnsplashApi = retrofit.create()


    override suspend fun getPhotos(): List<Photo> {
        return api.allPhotos()
    }

    override suspend fun searchPhotos(query: String): List<Photo> {
        return api.searchPhotos(query).results
    }
}