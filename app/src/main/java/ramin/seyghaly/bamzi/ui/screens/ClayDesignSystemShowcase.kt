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
import androidx.compose.material.icons.filled.Search
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
import ramin.seyghaly.bamzi.ui.components.ClayAvatar
import ramin.seyghaly.bamzi.ui.components.ClayButtonSize
import ramin.seyghaly.bamzi.ui.components.ClayCard
import ramin.seyghaly.bamzi.ui.components.ClayPrimaryButton
import ramin.seyghaly.bamzi.ui.components.ClaySecondaryButton
import ramin.seyghaly.bamzi.ui.components.ClayStatItem
import ramin.seyghaly.bamzi.ui.components.ClayTextField
import ramin.seyghaly.bamzi.ui.components.OnlineBadge
import ramin.seyghaly.bamzi.ui.components.TagBadge
import ramin.seyghaly.bamzi.ui.theme.BamziTheme

@Composable
fun ClayDesignSystemShowcaseScreen(
    modifier: Modifier = Modifier,
    isDark: Boolean = false
) {
    var searchText by remember { mutableStateOf("") }

    BamziTheme(darkTheme = isDark) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                // Title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "سیستم طراحی بامزی",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "مجموعه کامپوننت‌های کلیمورفیک (Claymorphism)",
                            fontSize = 13.5.sp,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                    ClayAvatar(size = 48.dp, initials = "B")
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Badges & Chips
                Text(text = "نشان‌ها و بج‌ها (Badges)", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    OnlineBadge()
                    TagBadge(text = "۲ بازیکن", isPrimary = true)
                    TagBadge(text = "چندنفره آنلاین", isPrimary = false)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Stat Counters
                Text(text = "آمار و ارقام (Stats)", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ClayStatItem(title = "بازی آنلاین فعال", value = "۳", modifier = Modifier.weight(1f), isDark = isDark)
                    ClayStatItem(title = "اتاق باز در انتظار", value = "۰", modifier = Modifier.weight(1f), isDark = isDark)
                    ClayStatItem(title = "محاسبه ELO", value = "۱۸۵۰", modifier = Modifier.weight(1f), isDark = isDark)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Text Field (Inset)
                Text(text = "فیلد جستجو (ClayTextField)", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(10.dp))
                ClayTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = "جستجو در بازی‌ها و ابزارها...",
                    isDark = isDark,
                    leadingIcon = Icons.Default.Search
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Buttons Showcase
                Text(text = "دکمه‌ها (ClayButtons)", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ClayPrimaryButton(
                        text = "شروع بازی",
                        onClick = {},
                        leadingIcon = Icons.Default.PlayArrow,
                        size = ClayButtonSize.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    ClaySecondaryButton(
                        text = "تنظیمات",
                        onClick = {},
                        isDark = isDark,
                        leadingIcon = Icons.Default.Settings,
                        size = ClayButtonSize.Medium,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Cards Showcase
                Text(text = "کارت‌ها (ClayCards)", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(10.dp))
                ClayCard(isDark = isDark, onClick = {}) {
                    Text(
                        text = "کارت کلیمورفیک تعاملی",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "این کارت دارای انیمیشن پرش (Bouncy Tap) و سایه‌های دوگانه کلیمورفیک است.",
                        fontSize = 13.5.sp,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    ClayPrimaryButton(
                        text = "ورود به میز بازی",
                        onClick = {},
                        size = ClayButtonSize.Small
                    )
                }
            }
        }
    }
}

@Preview(name = "Clay Design System Showcase - Light", showBackground = true, widthDp = 412, heightDp = 1000)
@Composable
fun ClayShowcaseLightPreview() {
    ClayDesignSystemShowcaseScreen(isDark = false)
}

@Preview(name = "Clay Design System Showcase - Dark", showBackground = true, widthDp = 412, heightDp = 1000)
@Composable
fun ClayShowcaseDarkPreview() {
    ClayDesignSystemShowcaseScreen(isDark = true)
}
