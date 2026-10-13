# Implementation Plan - Bamzi Android App (Theme, Colors & Claymorphism UI Components with Previews)

This plan outlines the implementation of the custom **Claymorphism** design system extracted from the provided web app static styles (`design-system.css`, `components.css`), including Color tokens, Theme (Light/Dark), Claymorphism modifiers, and reusable UI components with Jetpack Compose Previews.

## Proposed Changes

### Theme & Colors (Claymorphism)
- #### [MODIFY] [Color.kt](file:///C:/Projects/bamzi-android/app/src/main/java/ramin/seyghaly/bamzi/ui/theme/Color.kt)
  - Define exact color tokens from `design-system.css` (`--color-primary`, `--color-deep`, `--color-bg-primary`, `--color-bg-surface`, etc. for both light and dark modes).
- #### [MODIFY] [Theme.kt](file:///C:/Projects/bamzi-android/app/src/main/java/ramin/seyghaly/bamzi/ui/theme/Theme.kt)
  - Implement BamziTheme with Light/Dark color schemes and Vazirmatn typography.
- #### [NEW] [ClayModifiers.kt](file:///C:/Projects/bamzi-android/app/src/main/java/ramin/seyghaly/bamzi/ui/theme/ClayModifiers.kt)
  - Custom Modifier extensions for Claymorphic cards (`clayCard`), buttons (`clayButton`), and inset fields (`clayInset`) matching CSS shadow formulas.

### UI Components & Previews
- #### [NEW] [ClayButton.kt](file:///C:/Projects/bamzi-android/app/src/main/java/ramin/seyghaly/bamzi/ui/components/ClayButton.kt)
  - Primary and secondary claymorphic buttons with `@Preview` annotations.
- #### [NEW] [ClayCard.kt](file:///C:/Projects/bamzi-android/app/src/main/java/ramin/seyghaly/bamzi/ui/components/ClayCard.kt)
  - Elevated and surface cards with `@Preview` annotations.
- #### [NEW] [ClayTextField.kt](file:///C:/Projects/bamzi-android/app/src/main/java/ramin/seyghaly/bamzi/ui/components/ClayTextField.kt)
  - Inset search/text input fields with `@Preview` annotations.

## Verification Plan

### Automated Tests
- Gradle build test (`./gradlew app:assembleDebug`).
