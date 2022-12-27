package com.example.workshop.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun HomeScreen() {
    val vm: HomeViewModel = viewModel()
    val state = vm.photos.collectAsState().value

    LazyColumn {

        if(state.loading) {
            item {
                CircularProgressIndicator()
            }
        }

        items(state.photos) {
            AsyncImage(model = it.links.download, contentDescription = it.description)
        }
    }
}
