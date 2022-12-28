package com.example.workshop

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workshop.home.HomeScreen

@Composable
fun WorkshopNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel = hiltViewModel(it), onNavigateToUser = { user ->
                navController.navigate("user/${user.id}")
            })
        }

        composable("user/{userId}") {
            val userId = it.arguments?.getString("userId")
            Text("TODO: User Screen: $userId")
        }

    }

}