package de.niilz.kmp.workoutlog2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// Border radius scale: small=4, medium=8, large=16, extraLarge≈pill
private val WorkoutShapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(4.dp),
    small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(999.dp),
)

// Elevation tokens (dp) — use as ShadowElevation / TonalElevation on Material surfaces
object Elevation {
    val Card = 2.dp
    val Modal = 8.dp
    val Fab = 16.dp
}

// Spacing tokens (8pt grid)
object Spacing {
    val xs = 4.dp
    val sm = 8.dp
    val md = 12.dp
    val lg = 16.dp
    val xl = 24.dp
    val xxl = 32.dp
    val xxxl = 48.dp
    val huge = 64.dp
}

@Composable
fun WorkoutTrackerTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = WorkoutLightColorScheme,
        typography = WorkoutTypography,
        shapes = WorkoutShapes,
        content = content,
    )
}
