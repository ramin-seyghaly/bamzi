package ramin.seyghaly.bamzi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ramin.seyghaly.bamzi.ui.screens.home.HomeScreen

@Serializable
object HomeRoute

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(
                onLoginClick = {},
                onRegisterClick = {}
            )
        }
    }
}
