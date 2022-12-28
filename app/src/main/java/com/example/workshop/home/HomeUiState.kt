package com.example.workshop.home

import com.example.workshop.unsplash.model.Photo

data class HomeUiState(
    val loading: Boolean,
    val photos: List<Photo>,
    val error: String?,
)

sealed class HomeUiStateV2 {
    data class Error(val message: String) : HomeUiStateV2()
    data class Content(val photos: List<Photo>) :
        HomeUiStateV2()

    object Loading : HomeUiStateV2()
}

sealed class HomeEvents {
    object ShowMessage: HomeEvents()
}
