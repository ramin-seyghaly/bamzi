package ramin.seyghaly.bamzi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val shape = RoundedCornerShape(42.dp) // --radius-xl matching web app navbar (.bamzi-navbar)

    // Outer container with padding to allow the large soft colored shadow to spread freely
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape)
                .clayCard(isDark = isDark, shape = shape, elevation = 20.dp) // Main toolbar card view with prominent 20dp Z elevation
                .padding(horizontal = 22.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Right side: Logo with its own 3D card shadow
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .clayCard(isDark = isDark, shape = RoundedCornerShape(14.dp), elevation = 6.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(Color(0xFF9B51E0), Color(0xFF7E30E1), Color(0xFF49108B))
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🎮", fontSize = 20.sp)
                }

                Column {
                    Text(
                        text = "بامزی",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "GAMES BAMZI",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    )
                }
            }

            // Left side: Theme Toggle & Auth Buttons (Each with its own card/button shadow)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Theme Toggle Circular Button
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .clayButtonSecondary(isDark = isDark, shape = CircleShape)
                        .clayClickable(onClick = onThemeToggle),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                        contentDescription = "Toggle Theme",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }

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
