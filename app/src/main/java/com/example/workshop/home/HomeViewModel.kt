package com.example.workshop.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workshop.unsplash.api.UnsplashApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val api: UnsplashApi) : ViewModel() {


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

        val response = api.allPhotos()

        _photos.update { it.copy(photos = response, loading = false) }
    }



}
