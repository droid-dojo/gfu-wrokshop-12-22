package com.example.workshop.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workshop.search.ui.SearchUiState
import com.example.workshop.unsplash.api.UnsplashApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val api: UnsplashApi
) : ViewModel() {

    private val _search = MutableStateFlow("")

    private val _results = _search
        .debounce(500)
        .map { api.searchPhotos(it).results }

    val state = combine(_search, _results) { search, results ->
        SearchUiState(
            searchText = search,
            results = results
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = SearchUiState("", emptyList())
    )

    fun updateSearchString(newSearch: String) {
        _search.update { newSearch }
    }

}
