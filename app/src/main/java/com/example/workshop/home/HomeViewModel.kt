package com.example.workshop.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workshop.unsplash.api.UnsplashApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create

class HomeViewModel : ViewModel() {

    private val json = Json { ignoreUnknownKeys = true }
    private val converter = json.asConverterFactory("application/json".toMediaType())

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(converter)
        .build()

    private val api: UnsplashApi = retrofit.create()

    private val _photos = MutableStateFlow(
        HomeUiState(
            loading = true,
            photos = emptyList(),
            error = null
        )
    )

    val photos: StateFlow<HomeUiState> = _photos


    init {
        viewModelScope.launch { loadPhotos() }
    }

    private suspend fun loadPhotos() {
        _photos.update { it.copy(loading = true) }

        val response = api.allPhotos(ApiKey)

        _photos.update { it.copy(photos = response, loading = false) }
    }


    private companion object {
        const val ApiKey = "<REPLACE-ME>"
    }

}
