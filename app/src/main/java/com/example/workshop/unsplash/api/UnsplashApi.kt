package com.example.workshop.unsplash.api

import com.example.workshop.unsplash.model.Photo
import retrofit2.http.GET

interface UnsplashApi {
    @GET("photos")
    suspend fun allPhotos(): List<Photo>
}
