package de.niilz.kmp.workoutlog2.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import de.niilz.kmp.workoutlog2.model.Workout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListScreen(
    onBack: () -> Unit,
) {
    val sampleWorkouts = remember {
        listOf(
            Workout(id = 1, name = "Push Day"),
            Workout(id = 2, name = "Pull Day"),
            Workout(id = 3, name = "Leg Day"),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Select Workout",
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text(text = "←")
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: add workout */ }) {
                Text(text = "+")
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            items(sampleWorkouts) { workout ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = workout.name,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* TODO: start workout */ },
                )
                HorizontalDivider()
            }
        }
    }
}
