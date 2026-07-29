# Fix Grid Alignment and Text Wrapping in HomeScreen

The user reported that items in the "Group Games" grid are not aligned properly, specifically pointing out that text wrapping (two-lined text) is causing the misalignment. Investigation also showed inconsistent padding and lack of fixed heights in `GameCard`.

## Proposed Changes

### [app](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app)

#### [MODIFY] [HomeScreen.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/home/HomeScreen.kt)
- Update `LazyVerticalGrid` to use `horizontalArrangement = Arrangement.spacedBy(16.dp)` and `verticalArrangement = Arrangement.spacedBy(16.dp)`.
- Use `contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)`.
- Remove redundant `padding` from `modifier` arguments passed to `GameCard` and `ToolCard`.

#### [MODIFY] [GameCard.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/components/GameCard.kt)
- Remove hardcoded `padding(8.dp)` from the `Card`.
- Apply `Modifier.height(160.dp)` (or suitable fixed height) to ensure all cards align perfectly even with different text lengths.
- Set `minLines = 2` for the subtitle (or title if needed) to reserve space for the second line even if it's not present.
- Use `Modifier.fillMaxWidth()` to let the grid manage width.

#### [MODIFY] [ToolCard.kt](file:///C:/Users/ramin/OneDrive/Desktop/Projects/bamzi-app/app/src/main/java/ramin/seyghaly/bamzi/ui/components/ToolCard.kt)
- Remove hardcoded `padding(4.dp)` from the `Card`.
- Change `width = 110.dp` to `fillMaxWidth()` to properly fill the grid cells.
- Keep fixed `height = 100.dp`.

## Verification Plan

### Automated Tests
- Render `HomeScreenFaPreview` and verify the grid items are aligned and spacing is uniform.
- Verify the build passes.

### Manual Verification
- Deploy to a device to ensure the UI looks correct in both RTL and LTR modes.

## Verification Plan

### Automated Tests
- Run Compose Previews for `HomeScreen` and verify the layout visually.
- Verify the build passes.

### Manual Verification
- Deploy to a device/emulator to check the spacing and alignment in RTL (Persian) and LTR.
