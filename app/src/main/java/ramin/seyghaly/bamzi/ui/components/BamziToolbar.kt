package ramin.seyghaly.bamzi.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ramin.seyghaly.bamzi.ui.theme.BamziTheme
import ramin.seyghaly.bamzi.ui.theme.clayButtonSecondary
import ramin.seyghaly.bamzi.ui.theme.clayCard

@Composable
fun BamziToolbar(
    isDark: Boolean,
    onThemeToggle: () -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(36.dp)

    // Outer container with matching 20.dp horizontal padding to align with items
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .shadow(elevation = 10.dp, shape = shape)
                .fillMaxWidth()
                .clip(shape)
                .clayCard(isDark = isDark, shape = shape, elevation = 16.dp)
                .padding(horizontal = 16.dp, vertical = 10.dp), // Thinner height
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left side: Theme Toggle Circular Button
            Box(
                modifier = Modifier
                    .shadow(elevation = 10.dp, shape = shape)
                    .size(38.dp)
                    .clip(CircleShape)
                    .clayButtonSecondary(isDark = isDark, shape = CircleShape)
                    .clayClickable(onClick = onThemeToggle),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = "Toggle Theme",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Right side: Auth Buttons (Login & Register)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Login Button
                ClaySecondaryButton(
                    text = "ورود",
                    onClick = onLoginClick,
                    isDark = isDark,
                    size = ClayButtonSize.Small
                )

                // Register Button
                ClayPrimaryButton(
                    text = "ثبت‌نام",
                    onClick = onRegisterClick,
                    size = ClayButtonSize.Small
                )
            }
        }
    }
}

@Preview(name = "Bamzi Toolbar - Light", showBackground = true, backgroundColor = 0xFFF3F8FF)
@Composable
fun BamziToolbarLightPreview() {
    BamziTheme(darkTheme = false) {
        BamziToolbar(
            isDark = false,
            onThemeToggle = {},
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}

@Preview(name = "Bamzi Toolbar - Dark", showBackground = true, backgroundColor = 0xFF120924)
@Composable
fun BamziToolbarDarkPreview() {
    BamziTheme(darkTheme = true) {
        BamziToolbar(
            isDark = true,
            onThemeToggle = {},
            onLoginClick = {},
            onRegisterClick = {}
        )
    }
}
