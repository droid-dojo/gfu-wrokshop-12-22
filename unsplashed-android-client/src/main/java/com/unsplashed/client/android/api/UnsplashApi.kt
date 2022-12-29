package com.unsplashed.client.android.api

import com.unsplashed.client.model.Photo
import com.unsplashed.client.model.SearchResultPage
import retrofit2.http.GET
import retrofit2.http.Query

internal interface UnsplashApi {
    @GET("photos?limit=50")
    suspend fun allPhotos(): List<Photo>

    @GET("search/photos")
    suspend fun searchPhotos(@Query("query") search: String): SearchResultPage
}
