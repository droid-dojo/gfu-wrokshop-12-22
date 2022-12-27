package com.example.workshop.home

import com.example.workshop.unsplash.model.Photo

data class HomeUiState(
    val loading: Boolean,
    val photos: List<Photo>,
    val error: String?,
)