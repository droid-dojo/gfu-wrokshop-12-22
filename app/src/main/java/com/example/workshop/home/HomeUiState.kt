package com.example.workshop.home

import com.unsplashed.client.model.Photo

data class HomeUiState(
    val loading: Boolean,
    val photos: List<com.unsplashed.client.model.Photo>,
    val error: String?,
)

sealed class HomeUiStateV2 {
    data class Error(val message: String) : HomeUiStateV2()
    data class Content(val photos: List<com.unsplashed.client.model.Photo>) :
        HomeUiStateV2()

    object Loading : HomeUiStateV2()
}

sealed class HomeEvents {
    object ShowMessage: HomeEvents()
}
