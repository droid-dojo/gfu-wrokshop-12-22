package com.example.workshop.unsplash.api

import com.example.workshop.unsplash.model.Photo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun allPhotos(
        @Query("client_id") clientId: String
    ) : List<Photo>

    @GET("photos")
    suspend fun allPhotosHeaders(
        @Header("Authorization") authorization: String
    ) : List<Photo>
}