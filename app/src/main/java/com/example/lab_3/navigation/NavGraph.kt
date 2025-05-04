package com.example.lab_3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab_3.ui.screens.home.HomeScreen
import com.example.lab_3.ui.screens.category.CategoryScreen
import com.example.lab_3.ui.screens.details.DetailsScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Category.route) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type") ?: ""
            CategoryScreen(navController, type)
        }
        composable(Screen.Details.route) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            DetailsScreen(navController, id)
        }
    }
}
