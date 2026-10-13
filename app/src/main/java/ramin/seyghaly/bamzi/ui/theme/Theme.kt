package ramin.seyghaly.bamzi.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BamziDarkPrimary,
    onPrimary = BamziDarkTextOnPrimary,
    primaryContainer = BamziDarkDeep,
    secondary = BamziDarkPrimaryLight,
    background = BamziDarkBgPrimary,
    surface = BamziDarkBgSurface,
    onBackground = BamziDarkTextMain,
    onSurface = BamziDarkTextMain
)

private val LightColorScheme = lightColorScheme(
    primary = BamziLightPrimary,
    onPrimary = BamziLightTextOnPrimary,
    primaryContainer = BamziLightDeep,
    secondary = BamziLightPrimaryLight,
    background = BamziLightBgPrimary,
    surface = BamziLightBgSurface,
    onBackground = BamziLightTextMain,
    onSurface = BamziLightTextMain
)

@Composable
fun BamziTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disabled to preserve Bamzi custom claymorphic brand palette
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
