# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build Android debug APK
./gradlew :androidApp:assembleDebug

# Run shared KMP unit tests
./gradlew :composeApp:allTests

# Run Android unit tests
./gradlew :androidApp:test

# Run Android instrumented tests (requires connected device/emulator)
./gradlew :androidApp:connectedAndroidTest
```

iOS: open `iosApp/` directory in Xcode and run from there.

## Architecture

The project is split into two Gradle modules:

- **`:composeApp`** — Kotlin Multiplatform library (`com.android.kotlin.multiplatform.library` plugin). Contains all shared UI and business logic. Targets `androidLibrary`, `iosArm64`, and `iosSimulatorArm64`. The root composable is `App()` in `commonMain`.

- **`:androidApp`** — Standard Android application (`com.android.application`). Has no logic of its own — it depends on `:composeApp` and calls `App()` from its `MainActivity`. All Android launcher icons and the manifest live here.

Platform-specific code in `:composeApp` uses Kotlin's `expect`/`actual` pattern (see `Platform.kt` in each source set).

### Source Sets in `:composeApp`

```
composeApp/src/
├── commonMain/   # Shared UI (App.kt) and logic
├── androidMain/  # Android actuals
└── iosMain/      # iOS actuals
```

Shared image/vector assets go in `commonMain/composeResources/drawable/` and are accessed via the generated `Res` class:
```kotlin
painterResource(Res.drawable.my_asset)
```

### Theming

No custom design system exists yet — the app uses Material 3 defaults via `MaterialTheme { }`. To add custom theming, create `ui/theme/Color.kt`, `Type.kt`, and `Theme.kt` under `commonMain`.

### Dependency Management

All versions are centralized in `gradle/libs.versions.toml`. Add new dependencies there and reference them via `libs.*` in build files.
