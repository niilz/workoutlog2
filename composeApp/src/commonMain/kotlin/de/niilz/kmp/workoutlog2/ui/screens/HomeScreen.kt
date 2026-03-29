package de.niilz.kmp.workoutlog2.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import de.niilz.kmp.workoutlog2.ui.theme.Spacing
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    onSelectWorkout: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = Spacing.xl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Workout Tracker",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(Spacing.huge))

            Button(
                onClick = onSelectWorkout,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Select Workout",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }

            Spacer(modifier = Modifier.height(Spacing.lg))

            OutlinedButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Previous Workouts — coming soon!")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Previous Workouts",
                    style = MaterialTheme.typography.headlineSmall,
                )
            }
        }
    }
}
