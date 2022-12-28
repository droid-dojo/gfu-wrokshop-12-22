package com.example.workshop.unsplash.di

import com.unsplashed.client.UnsplashedClient
import com.unsplashed.client.android.UnsplashedAndroidClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun client(): UnsplashedClient = UnsplashedAndroidClient(
        "knOufpxjluVRpKx3PAa9-z34D7F7rg8q4JzzRQHPMfo"
    )


}
