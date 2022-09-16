package ru.atmosphereapp.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.atmosphereapp.movieapp.screens.detail.DetailScreen
import ru.atmosphereapp.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val nanController = rememberNavController()

    NavHost(navController = nanController,
        startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = nanController)
        }

        composable(MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })) {
            backStackEntry->

            DetailScreen(navController = nanController, backStackEntry.arguments?.getString("movie"))
        }
    }
}