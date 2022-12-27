package com.example.workshop.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.photos.collectAsState()

    HomeScreen(state = state)
}

@Composable
internal fun HomeScreen(state: HomeUiState) {
    LazyColumn {
        if (state.loading) {
            item {
                CircularProgressIndicator()
            }
        }

        items(state.photos) {
            AsyncImage(model = it.links.download, contentDescription = it.description)
        }
    }
}

@Preview
@Composable
private fun HomeLoadingPreview() {
    HomeScreen(state = HomeUiState(loading = true, emptyList(), null))
}

@Preview
@Composable
private fun EmptyList() {
    HomeScreen(state = HomeUiState(loading = false, emptyList(), null))
}