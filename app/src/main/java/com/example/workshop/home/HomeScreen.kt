package com.example.workshop.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.workshop.unsplash.model.User

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(),
               onNavigateToUser: (User) -> Unit) {

    val context = LocalContext.current

    LaunchedEffect(context, viewModel) {
        viewModel.events.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    //v1
//    val state by viewModel.photos.collectAsState()
//    HomeScreen(state = state)

    //v2
    val state2 by viewModel.photosV2.collectAsState()
    HomeScreen(stateV2 = state2, onNavigateToUser = onNavigateToUser)


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(stateV2: HomeUiStateV2, onNavigateToUser: (User) -> Unit) {

    when (stateV2) {
        is HomeUiStateV2.Content -> LazyColumn {
            items(stateV2.photos) {
                Card(onClick = { onNavigateToUser(it.user)}) {
                    AsyncImage(model = it.links.download, contentDescription = it.description)
                }
            }
        }

        is HomeUiStateV2.Error -> Text(
                modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(),
                text = "Fehler: ${stateV2.message}"
        )

        HomeUiStateV2.Loading -> CircularProgressIndicator(
                modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
        )
    }
}

@Composable
internal fun HomeScreen(state: HomeUiState) {
    Box {

        LazyColumn {
            items(state.photos) {
                AsyncImage(model = it.links.download, contentDescription = it.description)
            }
        }

        if (state.loading) {
            CircularProgressIndicator(
                    modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
            )
        }

        if (!state.error.isNullOrBlank()) {
            Text(
                    modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(),
                    text = "Fehler: ${state.error}"
            )
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