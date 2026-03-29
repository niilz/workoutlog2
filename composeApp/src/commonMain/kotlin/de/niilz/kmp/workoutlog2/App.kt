package de.niilz.kmp.workoutlog2

import androidx.compose.runtime.Composable
import de.niilz.kmp.workoutlog2.navigation.AppNavigation
import de.niilz.kmp.workoutlog2.ui.theme.WorkoutTrackerTheme

@Composable
fun App() {
    WorkoutTrackerTheme {
        AppNavigation()
    }
}
