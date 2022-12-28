package com.unsplashed.client

import com.unsplashed.client.model.Photo

interface UnsplashedClient {
    suspend fun getPhotos() : List<Photo>
    suspend fun searchPhotos(query: String): List<Photo>
}