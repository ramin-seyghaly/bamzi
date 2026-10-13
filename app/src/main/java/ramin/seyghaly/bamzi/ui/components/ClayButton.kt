package ramin.seyghaly.bamzi.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramin.seyghaly.bamzi.ui.theme.BamziTheme
import ramin.seyghaly.bamzi.ui.theme.clayButtonPrimary
import ramin.seyghaly.bamzi.ui.theme.clayButtonSecondary

@Composable
fun ClayPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    val shape = RoundedCornerShape(22.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .clayButtonPrimary(shape)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 8.dp), // Sleeker, lower height matching web buttons
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

@Composable
fun ClaySecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean = false,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    val shape = RoundedCornerShape(22.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .clayButtonSecondary(isDark = isDark, shape = shape)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 8.dp), // Sleeker, lower height matching web buttons
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = if (isDark) Color(0xFFF3F8FF) else Color(0xFF1D1338)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                color = if (isDark) Color(0xFFF3F8FF) else Color(0xFF1D1338),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false
            )
        }
    }
}

@Preview(name = "Light Buttons")
@Composable
fun ClayButtonLightPreview() {
    BamziTheme(darkTheme = false) {
        androidx.compose.foundation.layout.Column(modifier = Modifier.padding(16.dp)) {
            ClayPrimaryButton(text = "شروع بازی", onClick = {})
            Spacer(modifier = Modifier.height(16.dp))
            ClaySecondaryButton(text = "تنظیمات", onClick = {}, isDark = false)
        }
    }
}

@Preview(name = "Dark Buttons")
@Composable
fun ClayButtonDarkPreview() {
    BamziTheme(darkTheme = true) {
        androidx.compose.foundation.layout.Column(modifier = Modifier.padding(16.dp)) {
            ClayPrimaryButton(text = "شروع بازی", onClick = {})
            Spacer(modifier = Modifier.height(16.dp))
            ClaySecondaryButton(text = "تنظیمات", onClick = {}, isDark = true)
        }
    }
}
