package de.niilz.kmp.workoutlog2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.niilz.kmp.workoutlog2.ui.screens.HomeScreen
import de.niilz.kmp.workoutlog2.ui.screens.WorkoutListScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object WorkoutList : Screen("workout_list")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onSelectWorkout = { navController.navigate(Screen.WorkoutList.route) },
            )
        }
        composable(Screen.WorkoutList.route) {
            WorkoutListScreen(
                onBack = { navController.popBackStack() },
            )
        }
    }
}
