package com.example.lab_3.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Category : Screen("category/{type}") {
        fun createRoute(type: String) = "category/$type"
    }
    object Details : Screen("details/{id}") {
        fun createRoute(id: String) = "details/$id"
    }
}
