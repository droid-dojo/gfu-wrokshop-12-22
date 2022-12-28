package com.example.workshop

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feature.search.ui.SearchScreen
import com.example.workshop.home.ui.HomeScreen

@Composable
fun WorkshopNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = hiltViewModel(it),
                onNavigateToUser = { user ->
                    Log.d("Foo", "${user.links.portfolio}")
                    navController.navigate("user/${user.id}")
                },
                onNavigateToSearch = {
                    navController.navigate("search")
                }
            )
        }

        composable("user/{userId}") {
            val userId = it.arguments?.getString("userId") ?: throw IllegalStateException()
            Text("TODO: User Screen: $userId")
        }

        composable("search") {
            SearchScreen(vm = hiltViewModel(it))
        }

    }

}
