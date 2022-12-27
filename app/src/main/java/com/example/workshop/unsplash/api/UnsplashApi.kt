package com.example.workshop.unsplash.api

import com.example.workshop.unsplash.model.Photo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun allPhotos() : List<Photo>
}