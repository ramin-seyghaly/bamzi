package ramin.seyghaly.bamzi.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramin.seyghaly.bamzi.ui.components.ClayCard
import ramin.seyghaly.bamzi.ui.components.ClayPrimaryButton
import ramin.seyghaly.bamzi.ui.components.ClaySecondaryButton
import ramin.seyghaly.bamzi.ui.components.ClayTextField
import ramin.seyghaly.bamzi.ui.theme.BamziTheme

@Composable
fun BamziShowcaseScreen(
    modifier: Modifier = Modifier,
    isDark: Boolean = false
) {
    var searchQuery by remember { mutableStateOf("") }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "بامزی (Bamzi)",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "سیستم طراحی کلیمورفیک",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Search Bar (Inset ClayTextField)
            ClayTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = "جستجو در بازی‌ها و ابزارها...",
                isDark = isDark
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Buttons Section
            Text(
                text = "دکمه‌های کلیمورفیک (Buttons)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ClayPrimaryButton(
                    text = "شروع بازی",
                    onClick = {},
                    icon = Icons.Default.PlayArrow,
                    modifier = Modifier.weight(1f)
                )
                ClaySecondaryButton(
                    text = "تنظیمات",
                    onClick = {},
                    isDark = isDark,
                    icon = Icons.Default.Settings,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Cards Section
            Text(
                text = "کارت‌های کلیمورفیک (Cards)",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))

            ClayCard(isDark = isDark) {
                Text(
                    text = "بازی آنلاین شطرنج",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "با دوستان خود به صورت آنلاین بازی کنید و امتیاز بگیرید.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(16.dp))
                ClayPrimaryButton(
                    text = "ورود به میز",
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            ClayCard(isDark = isDark) {
                Text(
                    text = "ابزار ساعت و تایمر",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "تایمر حرفه‌ای برای مديريت زمان فعالیت‌ها.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
    }
}

// Previews for different screen views (Phone Light, Tablet Dark, etc.)
@Preview(name = "Phone - Light Mode", device = "spec:width=411dp,height=891dp", showBackground = true)
@Composable
fun BamziShowcasePhoneLightPreview() {
    BamziTheme(darkTheme = false) {
        BamziShowcaseScreen(isDark = false)
    }
}

@Preview(name = "Tablet - Dark Mode", device = "spec:width=1280dp,height=800dp,dpi=240", showBackground = true)
@Composable
fun BamziShowcaseTabletDarkPreview() {
    BamziTheme(darkTheme = true) {
        BamziShowcaseScreen(isDark = true)
    }
}
