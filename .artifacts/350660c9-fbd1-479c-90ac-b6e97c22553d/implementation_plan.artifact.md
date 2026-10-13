# Implementation Plan - UI Redesign of Menu Screen

Redesign the Home/Menu screen to match the provided image, including color scheme, card styles, and layout refinements.

## User Review Required

> [!IMPORTANT]
> The redesign changes the card style from a gradient-background icon to a pastel-colored circular background with colored icons. The "Recent Games" section also gets a significant layout update.

## Proposed Changes

### [Theme & Colors]

#### [MODIFY] [Color.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/theme/Color.kt)
- Add specific colors for icons and their pastel backgrounds:
    - `BlueIcon`, `BlueBg`
    - `RedIcon`, `RedBg`
    - `OrangeIcon`, `OrangeBg`
    - `GreyIcon`, `GreyBg`
    - `DeepBlue` (for buttons)

### [Data Models]

#### [MODIFY] [HomeUiState.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/home/HomeUiState.kt)
- Update `HomeItem` and `RecentGame` to include `iconColor` and `iconBgColor` instead of (or in addition to) gradients.

#### [MODIFY] [HomeViewModel.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/home/HomeViewModel.kt)
- Update the initialization data with the new color palette and correct icons/names matching the image.

### [UI Components]

#### [MODIFY] [GameCard.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/components/GameCard.kt)
- Change icon container to use a solid pastel background.
- Change icon tint to use the specific icon color.
- Refine card elevation and border.

#### [MODIFY] [ToolCard.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/components/ToolCard.kt)
- Similar updates as `GameCard` for consistency.

#### [MODIFY] [QuickActionButton.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/components/QuickActionButton.kt)
- Update default colors and allow customization.

#### [MODIFY] [HomeBanner.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/components/HomeBanner.kt)
- Tweak pagination dots and card styling.

### [Screens]

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/home/HomeScreen.kt)
- Update `SectionHeader` with larger, bolder text.
- Update `RecentGameCard` to match the image (ongoing game with blue border and button).
- Update `QuickActionRow` with specific social buttons (Phone, Telegram, Play).
- Update `mapIcon` function to support all required icons.

## Verification Plan

### Automated Tests
- Run `gradlew :app:assembleDebug` to ensure no build errors.

### Manual Verification
- Review the `HomeScreenFaPreview` in Android Studio to verify the visual changes match the image.
