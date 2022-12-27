package com.example.workshop.unsplash.di

import com.example.workshop.unsplash.api.ApiKeyInterceptor
import com.example.workshop.unsplash.api.Logger
import com.example.workshop.unsplash.api.UnsplashApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun okhttpClient() = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor)
        .addInterceptor(Logger)
        .build()

    @Provides
    fun retrofit(client: OkHttpClient): Retrofit {
        val json = Json { ignoreUnknownKeys = true }
        val converter = json.asConverterFactory("application/json".toMediaType())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(client)
            .addConverterFactory(converter)
            .build()

        return retrofit
    }

    @Provides
    fun unsplashApi(retrofit: Retrofit): UnsplashApi {
        val api: UnsplashApi = retrofit.create()
        return api
    }

}
