package com.example.workshop.unsplash.api

import com.example.workshop.unsplash.model.Photo
import com.example.workshop.unsplash.model.SearchResultPage
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun allPhotos(): List<Photo>

    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") search: String) : SearchResultPage
}
