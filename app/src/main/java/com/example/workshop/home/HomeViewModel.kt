package com.example.workshop.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workshop.unsplash.api.UnsplashApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val api: UnsplashApi) : ViewModel() {


    private val _photosV2 = MutableStateFlow<HomeUiStateV2>(HomeUiStateV2.Loading)

    private val _photos = MutableStateFlow(
        HomeUiState(
            loading = true,
            photos = emptyList(),
            error = null
        )
    )

    //Compile Safety warning
    val photos: StateFlow<HomeUiState> = _photos

    //magic boxing
    val photosV2 = _photosV2.asStateFlow()


    init {
        viewModelScope.launch { loadPhotos() }
    }

    private suspend fun loadPhotos() {
        //Zustand auf laden setzen
        _photos.update { it.copy(loading = true) }
        _photosV2.update { HomeUiStateV2.Loading }

        //Fehler Simulieren
        _photos.update { it.copy(error = "Fehler beim laden", photos = emptyList()) }
        _photosV2.update { HomeUiStateV2.Error("Fehler beim laden") }

        //Content update
        val response = api.allPhotos()
        _photos.update { it.copy(photos = response, loading = false, error = null) }
        _photosV2.update { HomeUiStateV2.Content(photos = response) }
    }


}
