package de.niilz.kmp.workoutlog2.model

data class WorkoutSet(
    val id: Int,
    val weight: Float,
    val reps: Int,
    val completed: Boolean = false,
)

data class Exercise(
    val id: Int,
    val name: String,
    val description: String = "",
    val sets: List<WorkoutSet> = emptyList(),
)

data class Workout(
    val id: Int,
    val name: String,
    val exercises: List<Exercise> = emptyList(),
)
