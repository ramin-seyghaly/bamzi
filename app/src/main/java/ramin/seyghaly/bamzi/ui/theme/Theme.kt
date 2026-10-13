package ramin.seyghaly.bamzi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

private val DarkColorScheme = darkColorScheme(
    primary = BamziDarkPrimary,
    onPrimary = BamziDarkTextOnPrimary,
    secondary = BamziDarkPrimaryLight,
    onSecondary = BamziDarkTextOnPrimary,
    background = BamziDarkBgPrimary,
    surface = BamziDarkBgSurface,
    onBackground = BamziDarkTextMain,
    onSurface = BamziDarkTextMain,
    surfaceVariant = BamziDarkBgSurfaceSoft,
    onSurfaceVariant = BamziDarkTextMuted
)

private val LightColorScheme = lightColorScheme(
    primary = BamziLightPrimary,
    onPrimary = BamziLightTextOnPrimary,
    secondary = BamziLightPrimaryLight,
    onSecondary = BamziLightTextOnPrimary,
    background = BamziLightBgPrimary,
    surface = BamziLightBgSurface,
    onBackground = BamziLightTextMain,
    onSurface = BamziLightTextMain,
    surfaceVariant = BamziLightBgSurfaceSoft,
    onSurfaceVariant = BamziLightTextMuted
)

@Composable
fun BamziTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
