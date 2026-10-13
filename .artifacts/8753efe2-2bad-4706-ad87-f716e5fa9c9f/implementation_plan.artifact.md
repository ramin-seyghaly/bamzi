# Implementation Plan - Game Hub App (گیم‌هاب)

Build a professional game management app with Clean Architecture using Hilt, ViewModel, Coroutines, Paging 3, Compose, and Navigation. The UI will strictly follow the provided design.

## Proposed Changes

### 1. Dependency Management
#### [MODIFY] [libs.versions.toml](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/gradle/libs.versions.toml)
- Add versions and libraries for:
    - Hilt (Dagger Hilt + Hilt Navigation Compose)
    - Navigation Compose (Type-safe with Serialization)
    - Paging 3
    - Coil (Image Loading)
    - Kotlin Serialization
#### [MODIFY] [build.gradle.kts](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/build.gradle.kts)
- Add Hilt and Serialization plugins.
#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/build.gradle.kts)
- Apply Hilt and Serialization plugins.
- Add all new dependencies.

### 2. Infrastructure Setup
#### [NEW] [BamziApplication.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/BamziApplication.kt)
- Annotate with `@HiltAndroidApp`.
#### [MODIFY] [AndroidManifest.xml](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/AndroidManifest.xml)
- Add `android:name=".BamziApplication"` and set `android:supportsRtl="true"`.

### 3. Theme & Resources
#### [MODIFY] [Color.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/theme/Color.kt)
- Add colors matching the design (Orange Primary, Soft Grey Background, etc.).
#### [MODIFY] [Theme.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/theme/Theme.kt)
- Configure the `MaterialTheme` to use the new color scheme.

### 4. UI Layer - Components
#### [NEW] Components Directory
- `GameCard.kt`: Professional card for the game grid.
- `ToolCard.kt`: Card for quick tools and settings.
- `HomeBanner.kt`: Rounded banner for the top section.
- `HomeHeader.kt`: Custom top bar with logo and icons.
- `QuickActionButton.kt`: Round buttons for secondary actions.

### 5. UI Layer - Home Screen
#### [NEW] [HomeViewModel.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/home/HomeViewModel.kt)
- Manage Home Screen state (Games list, Tools list, etc.) using `StateFlow`.
#### [NEW] [HomeScreen.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/home/HomeScreen.kt)
- Build the layout using the components, matching the visual structure:
    - Top Bar (Settings, Logo, Notifications)
    - Banner
    - Three round action buttons
    - Games Grid (2 columns)
    - Quick Game Settings (Horizontal list)
    - Quick Tools (Horizontal list)
    - Data Management (Row)
    - Bottom "Continue Game" bar

### 6. Navigation
#### [NEW] [NavGraph.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/navigation/NavGraph.kt)
- Set up `NavHost` with type-safe routes using Kotlin Serialization.
#### [MODIFY] [MainActivity.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/MainActivity.kt)
- Set up `NavHost` and `Hilt` entry point.

## Verification Plan
### Automated Tests
- Build and run the app to ensure no dependency conflicts.
- Verify Hilt injection in `HomeViewModel`.
### Manual Verification
- Deploy to an Android device/emulator.
- Inspect the UI to ensure it matches the provided image (colors, rounded corners, spacing, RTL support).
