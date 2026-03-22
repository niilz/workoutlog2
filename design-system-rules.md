# Design System Rules — Workout Tracker

This document defines the design system for the Workout Tracker app and guides Figma ↔ code integration via the Model Context Protocol.

---

## 1. Token Definitions

All design tokens are defined as Kotlin source files under:
```
composeApp/src/commonMain/kotlin/de/niilz/kmp/workoutlog2/ui/theme/
├── Color.kt    ← color tokens + MaterialColorScheme
├── Type.kt     ← typography scale
└── Theme.kt    ← WorkoutTrackerTheme composable, Spacing object, Elevation object
```

### Colors (`Color.kt`)

Design direction: light neutral base, electric-blue primary accent, high-contrast text.

| Token (Kotlin) | Hex | Role |
|----------------|-----|------|
| `PrimaryBlue` | `#3D5AFE` | Primary CTA, active state, accent |
| `PrimaryBlueContainer` | `#E8EAFF` | Chip background, tag background |
| `OnPrimaryBlue` | `#FFFFFF` | Text/icon on primary |
| `SecondaryTeal` | `#00BFA5` | Secondary actions, secondary highlights |
| `SecondaryTealContainer` | `#CCFFF7` | Secondary container fills |
| `BackgroundLight` | `#F8F9FA` | Screen background |
| `SurfaceLight` | `#FFFFFF` | Card / sheet surface |
| `OnBackgroundDark` | `#1A1A2E` | Primary text on background |
| `OnSurfaceVariant` | `#616161` | Secondary text, hints, captions |
| `SuccessGreen` | `#43A047` | Completed set/exercise indicator |
| `WarningOrange` | `#FB8C00` | Skipped set/exercise indicator |
| `DestructiveRed` | `#E53935` | Delete actions, error states |
| `DisabledGrey` | `#BDBDBD` | Disabled controls, outline |

The tokens are assembled into `WorkoutLightColorScheme` using Material 3's `lightColorScheme()`.

**Usage in composables:**
```kotlin
// Access via MaterialTheme after wrapping in WorkoutTrackerTheme
MaterialTheme.colorScheme.primary          // PrimaryBlue
MaterialTheme.colorScheme.background       // BackgroundLight
MaterialTheme.colorScheme.error            // DestructiveRed

// Semantic tokens not in Material slots — reference directly
import de.niilz.kmp.workoutlog2.ui.theme.SuccessGreen
import de.niilz.kmp.workoutlog2.ui.theme.WarningOrange
```

### Typography (`Type.kt`)

Typeface: **Inter** (system fallback). Two weights: Bold for display/headers, Normal for body.

| Kotlin slot | Figma style name | Size | Weight | Line height |
|-------------|-----------------|------|--------|-------------|
| `displayLarge` | Display | 32sp | Bold | 40sp |
| `headlineLarge` | H1 | 24sp | Bold | 32sp |
| `headlineMedium` | H2 | 20sp | SemiBold | 28sp |
| `headlineSmall` | H3 | 16sp | SemiBold | 24sp |
| `bodyLarge` | Body | 14sp | Normal | 20sp |
| `bodyMedium` | Body Small / Caption | 12sp | Normal | 16sp |
| `labelSmall` | Label / Overline | 11sp | Medium | 16sp |

**Usage:**
```kotlin
Text("Workout Name", style = MaterialTheme.typography.headlineLarge)
Text("4 sets · 80 kg", style = MaterialTheme.typography.bodyMedium)
```

### Spacing (`Theme.kt` → `Spacing` object)

8pt base grid. All padding/margin should use these tokens.

```kotlin
Spacing.xs   // 4.dp
Spacing.sm   // 8.dp
Spacing.md   // 12.dp
Spacing.lg   // 16.dp
Spacing.xl   // 24.dp
Spacing.xxl  // 32.dp
Spacing.xxxl // 48.dp
Spacing.huge // 64.dp
```

**Usage:**
```kotlin
Modifier.padding(horizontal = Spacing.lg, vertical = Spacing.sm)
```

### Elevation (`Theme.kt` → `Elevation` object)

```kotlin
Elevation.Card  // 2.dp  — workout/exercise list cards
Elevation.Modal // 8.dp  — bottom sheets, dialogs
Elevation.Fab   // 16.dp — floating action button
```

### Border Radius (Shapes)

Exposed via `MaterialTheme.shapes`:

| Shape slot | Radius | Use case |
|------------|--------|----------|
| `extraSmall` | 4dp | Input fields, chips |
| `small` | 8dp | Cards, list items |
| `medium` | 16dp | Bottom sheets, large cards |
| `extraLarge` | 999dp | Pill buttons, FAB |

---

## 2. Component Library

No component library exists yet — all screens are to be built. Components are defined as `@Composable` functions in `commonMain`. The planned components (from `milestone-plan.md` Milestone 2) are:

| Component | Purpose | Screen |
|-----------|---------|--------|
| `PrimaryButton` | Filled CTA with accent color | All |
| `SecondaryButton` | Outlined, secondary action | All |
| `DestructiveButton` | Red destructive action | Edit screens |
| `IconButton` | Icon-only tap target | Headers, list items |
| `WorkoutFab` | FAB with plus icon | Workout/exercise lists |
| `AppHeader` | Title + back icon + user name (right) | All screens |
| `TimerDisplay` | MM:SS readout + play/pause | Landing, active workout |
| `WorkoutListItem` | Title + subtitle + chevron | Workout selection |
| `ExerciseListItem` | Collapsed: name + set summary | Active workout |
| `ExerciseListItemExpanded` | Expanded with set rows | Active workout |
| `SetRow` | Weight input + reps input + check/skip | Active workout |
| `NumericInput` | Editable weight or reps field | Exercise/set edit |
| `TextInput` | Name and description fields | Exercise edit |
| `FilterChip` | Selectable filter tag | Statistics |
| `EmptyState` | Illustration + message + CTA | Landing, lists |

**Planned file location:**
```
composeApp/src/commonMain/kotlin/de/niilz/kmp/workoutlog2/ui/components/
```

**Component convention:**
```kotlin
@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) { ... }
```
- Always accept a `modifier: Modifier = Modifier` parameter
- Wrap state variants (default/pressed/disabled) as `enabled` or `state` parameters, not separate composables

---

## 3. Frameworks & Libraries

| Layer | Technology |
|-------|-----------|
| UI framework | Jetpack Compose / Compose Multiplatform 1.10.0 |
| Design system | Material 3 (`org.jetbrains.compose.material3:material3:1.10.0-alpha05`) |
| Platform targets | Android (minSdk 26, compileSdk 36), iOS (iosArm64, iosSimulatorArm64) |
| Language | Kotlin 2.3.0 |
| Build system | Gradle 9.x with Kotlin DSL, version catalog at `gradle/libs.versions.toml` |
| Lifecycle | `org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.9.6` |

No CSS, no web frameworks. All styling is done through Compose Modifiers and MaterialTheme.

---

## 4. Asset Management

### Shared assets (preferred for Figma exports)
- **Location:** `composeApp/src/commonMain/composeResources/drawable/`
- **Format:** XML Vector Drawables (`.xml`) or PNG/WebP
- **Access via generated `Res` class:**
```kotlin
import workoutlog2.composeapp.generated.resources.Res
import workoutlog2.composeapp.generated.resources.ic_timer

Image(painterResource(Res.drawable.ic_timer), contentDescription = "Timer")
```
- The `Res` class is auto-generated at build time — no manual registration needed

### Android-only resources
- `composeApp/src/androidMain/res/` — launcher icons only
- `androidApp/src/main/res/` — app launcher icons and `strings.xml`

### Naming convention
All drawable files: `snake_case`, prefixed by type:
- Icons: `ic_<name>.xml` (e.g. `ic_play.xml`, `ic_trash.xml`, `ic_check.xml`)
- Backgrounds: `bg_<name>.xml`
- Illustrations: `il_<name>.xml` (e.g. `il_empty_state.xml`)

---

## 5. Icon System

**Required icons** (from `milestone-plan.md` Milestone 1):

| Name | File | Usage |
|------|------|-------|
| Play | `ic_play.xml` | Timer play button |
| Pause | `ic_pause.xml` | Timer pause button |
| Plus | `ic_plus.xml` | FAB, add workout/exercise/set |
| Trash | `ic_trash.xml` | Delete workout/exercise/set |
| Check | `ic_check.xml` | Mark set/exercise complete |
| Skip | `ic_skip.xml` | Mark set/exercise skipped |
| Chevron right | `ic_chevron_right.xml` | List item navigation arrow |
| Back | `ic_back.xml` | Navigation back |
| Edit | `ic_edit.xml` | Edit workout/exercise name |
| Filter | `ic_filter.xml` | Statistics sort/filter |
| Calendar | `ic_calendar.xml` | Training calendar tab |
| Bar chart | `ic_bar_chart.xml` | Exercise chart tab |
| Timer | `ic_timer.xml` | Timer display icon |

**Icon style spec** (for Figma export):
- Size: 24×24dp
- Style: Stroke-based (not filled), 2px stroke weight, rounded line caps and joins
- Export as SVG, then convert to Android Vector Drawable (`.xml`) via Android Studio or `vd-tool`

**Usage pattern:**
```kotlin
Icon(
    painter = painterResource(Res.drawable.ic_plus),
    contentDescription = "Add exercise",
    tint = MaterialTheme.colorScheme.onPrimary,
    modifier = Modifier.size(24.dp),
)
```

---

## 6. Styling Approach

No CSS — all styling is Kotlin + Compose Modifiers.

### Global theme entry point
Every screen must be wrapped (directly or via root `App()`) in:
```kotlin
WorkoutTrackerTheme {
    // screen content
}
```
`App.kt` already wraps everything in `WorkoutTrackerTheme`.

### Pattern: background + padding
```kotlin
Column(
    modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .safeContentPadding()                         // always — handles iOS notch/home bar
        .padding(horizontal = Spacing.lg)
)
```
`safeContentPadding()` is **required** on all root layout containers to handle iOS safe areas and Android edge-to-edge.

### Pattern: card surface
```kotlin
Surface(
    shape = MaterialTheme.shapes.small,               // 8dp radius
    shadowElevation = Elevation.Card,                 // 2.dp
    color = MaterialTheme.colorScheme.surface,
) { ... }
```

### Responsive / adaptive layout
- Target frame sizes: **390×844** (iOS) and **360×800** (Android)
- Use `fillMaxWidth()` + `padding(horizontal = Spacing.lg)` instead of fixed widths
- No breakpoint system — layouts are single-column mobile-first

---

## 7. Screen Inventory & Structure

Screens are organized by feature (planned structure):
```
ui/
├── theme/              ← Color.kt, Type.kt, Theme.kt  (exists)
├── components/         ← shared atomic components      (to create)
├── home/               ← Landing screen                (to create)
├── workout/            ← Workout selection + detail + active workout (to create)
├── exercise/           ← Exercise edit screen          (to create)
└── statistics/         ← Stats hub, calendar, charts   (to create)
```

Each feature folder should contain:
- `<Feature>Screen.kt` — the `@Composable` screen function
- `<Feature>ViewModel.kt` — state holder (if needed)

### Screen list (from `app-concept.md`)
| Screen | Key UI elements |
|--------|----------------|
| Landing | User name (header right), "Select Workout" CTA, "Previous Workouts" CTA, optional timer + play/pause |
| Workout Selection | Scrollable workout list, FAB (add), per-item trash icon |
| Workout Detail/Edit | Workout name (editable), exercise list, add/remove exercises |
| Active Workout | Timer in header, expandable exercise rows, per-set check/skip/edit |
| Exercise Edit | Name, description, set list (weight + reps per set), add/remove sets |
| Statistics Hub | Tab nav: Training History, Calendar, Exercise Charts |
| Training History | List of past sessions with date, name, duration; expandable detail |
| Calendar View | Month grid, accent dot on workout days, tappable day summary |
| Exercise Bar Chart | Bars per exercise, sort chips (times/last/weight) |
| Exercise Detail Graph | Line graph of selected metric over time (volume, max weight, max reps) |

---

## 8. Figma ↔ Code Mapping

When translating Figma designs to Compose:

| Figma concept | Compose equivalent |
|---------------|-------------------|
| Frame / Auto Layout | `Column`, `Row`, `Box` with Modifiers |
| Fill color | `Modifier.background(color)` or `Surface(color = ...)` |
| Text style "H1" | `MaterialTheme.typography.headlineLarge` |
| Color variable "Primary" | `MaterialTheme.colorScheme.primary` |
| Corner radius 8 | `MaterialTheme.shapes.small` |
| Spacing 16 | `Spacing.lg` |
| Elevation 2 | `Elevation.Card` |
| Component state: disabled | `enabled = false` parameter |
| Component state: completed | `tint = SuccessGreen` / `color = SuccessGreen` |
| Component state: skipped | `tint = WarningOrange` / strikethrough `TextDecoration` |
| SVG icon export | Convert to `.xml` → `Res.drawable.ic_<name>` |

---

## 9. Figma Variable Naming Convention

When creating variables in Figma, use this naming so they map directly to Kotlin token names:

```
color/primary          → PrimaryBlue        (#3D5AFE)
color/primary-container → PrimaryBlueContainer (#E8EAFF)
color/secondary        → SecondaryTeal      (#00BFA5)
color/background       → BackgroundLight    (#F8F9FA)
color/surface          → SurfaceLight       (#FFFFFF)
color/on-background    → OnBackgroundDark   (#1A1A2E)
color/on-surface-variant → OnSurfaceVariant (#616161)
color/success          → SuccessGreen       (#43A047)
color/warning          → WarningOrange      (#FB8C00)
color/destructive      → DestructiveRed     (#E53935)
color/disabled         → DisabledGrey       (#BDBDBD)

spacing/xs  → 4   spacing/sm → 8   spacing/md → 12
spacing/lg  → 16  spacing/xl → 24  spacing/xxl → 32

radius/small → 8   radius/medium → 16   radius/pill → 999
```
