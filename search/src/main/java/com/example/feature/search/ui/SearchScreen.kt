package com.example.feature.search.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.feature.search.SearchViewModel

@Composable
fun SearchScreen(vm: SearchViewModel) {
    val state by vm.state.collectAsState()
    SearchScreen(state = state, onSearchUpdate = vm::updateSearchString)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(state: SearchUiState, onSearchUpdate: (String) -> Unit) {
    Scaffold(
        topBar = {
            OutlinedTextField(value = state.searchText, onValueChange = onSearchUpdate)
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(state.results) { photo ->
                AsyncImage(photo.links.download, contentDescription = null)
            }
        }
    }

}

internal data class SearchUiState(
    val searchText: String,
    val results: List<com.unsplashed.client.model.Photo>
)