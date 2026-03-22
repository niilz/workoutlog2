package de.niilz.kmp.workoutlog2.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// --- Palette ---
val PrimaryBlue = Color(0xFF3D5AFE)
val PrimaryBlueContainer = Color(0xFFE8EAFF)
val OnPrimaryBlue = Color(0xFFFFFFFF)
val OnPrimaryBlueContainer = Color(0xFF001088)

val SecondaryTeal = Color(0xFF00BFA5)
val SecondaryTealContainer = Color(0xFFCCFFF7)
val OnSecondaryTeal = Color(0xFFFFFFFF)
val OnSecondaryTealContainer = Color(0xFF003731)

val BackgroundLight = Color(0xFFF8F9FA)
val SurfaceLight = Color(0xFFFFFFFF)
val OnBackgroundDark = Color(0xFF1A1A2E)
val OnSurfaceDark = Color(0xFF1A1A2E)
val OnSurfaceVariant = Color(0xFF616161)

val OutlineColor = Color(0xFFBDBDBD)

// --- Semantic ---
val SuccessGreen = Color(0xFF43A047)
val WarningOrange = Color(0xFFFB8C00)
val DestructiveRed = Color(0xFFE53935)
val DisabledGrey = Color(0xFFBDBDBD)

// --- Color Schemes ---
val WorkoutLightColorScheme = lightColorScheme(
    primary = PrimaryBlue,
    onPrimary = OnPrimaryBlue,
    primaryContainer = PrimaryBlueContainer,
    onPrimaryContainer = OnPrimaryBlueContainer,
    secondary = SecondaryTeal,
    onSecondary = OnSecondaryTeal,
    secondaryContainer = SecondaryTealContainer,
    onSecondaryContainer = OnSecondaryTealContainer,
    background = BackgroundLight,
    onBackground = OnBackgroundDark,
    surface = SurfaceLight,
    onSurface = OnSurfaceDark,
    onSurfaceVariant = OnSurfaceVariant,
    error = DestructiveRed,
    outline = OutlineColor,
)
