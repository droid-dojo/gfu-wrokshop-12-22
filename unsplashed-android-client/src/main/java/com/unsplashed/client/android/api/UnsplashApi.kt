package com.unsplashed.client.android.api

import com.unsplashed.client.model.Photo
import com.unsplashed.client.model.SearchResultPage
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UnsplashApi {
    @GET("photos")
    suspend fun allPhotos(): List<com.unsplashed.client.model.Photo>

    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") search: String) : com.unsplashed.client.model.SearchResultPage
}
