package com.devspacecinenow

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun CineNowApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movieList") {
        composable(route = "movieList") {
            MovieListScreen(navController)
        }
        composable(
            route = "movieDetail" + "/{itemID}",
            arguments = listOf(navArgument("itemID"){
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val movieId = requireNotNull(backStackEntry.arguments?.getString("itemID"))
            MovieDetailScreen(movieId, navController)
        }
    }
}