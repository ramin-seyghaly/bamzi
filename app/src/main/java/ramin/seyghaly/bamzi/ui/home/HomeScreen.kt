package ramin.seyghaly.bamzi.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_6
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ramin.seyghaly.bamzi.ui.components.*
import ramin.seyghaly.bamzi.ui.theme.BackgroundLight
import ramin.seyghaly.bamzi.ui.theme.OrangePrimary
import ramin.seyghaly.bamzi.ui.theme.TextDark
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeScreenContent(uiState = uiState)
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Scaffold(
            topBar = { HomeHeader() },
            containerColor = BackgroundLight
        ) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = 24.dp)
            ) {
                // Banner
                item(key = "banner", span = { GridItemSpan(6) }) {
                    Column {
                        Spacer(modifier = Modifier.height(12.dp))
                        HomeBanner(banners = uiState.banners)
                    }
                }

                // Social Actions
                item(key = "social_actions", span = { GridItemSpan(6) }) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        QuickActionRow(actions = uiState.socialActions)
                    }
                }

                // Group Games Section
                item(key = "header_games", span = { GridItemSpan(6) }) {
                    SectionHeader(title = "بازی‌های گروهی")
                }
                items(uiState.games, key = { it.id }, span = { GridItemSpan(3) }) { game ->
                    GameCard(
                        title = game.title,
                        subtitle = game.about,
                        icon = mapIcon(game.iconRes),
                        gradientStart = Color(game.bgGradientStart),
                        gradientEnd = Color(game.bgGradientEnd),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                // Quick Settings Section
                item(key = "header_settings", span = { GridItemSpan(6) }) {
                    SectionHeader(title = "تنظیمات سریع بازی")
                }
                items(uiState.quickActions, key = { it.id }, span = { GridItemSpan(2) }) { action ->
                    ToolCard(
                        title = action.title,
                        icon = mapIcon(action.iconRes),
                        gradientStart = Color(action.bgGradientStart),
                        gradientEnd = Color(action.bgGradientEnd),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }

                // Other Tools Section
                item(key = "header_tools", span = { GridItemSpan(6) }) {
                    SectionHeader(title = "ابزارهای دیگر")
                }
                items(uiState.tools, key = { it.id }, span = { GridItemSpan(2) }) { tool ->
                    ToolCard(
                        title = tool.title,
                        icon = mapIcon(tool.iconRes),
                        gradientStart = Color(tool.bgGradientStart),
                        gradientEnd = Color(tool.bgGradientEnd),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }

                // Data Management Section
                item(key = "header_data", span = { GridItemSpan(6) }) {
                    SectionHeader(title = "مدیریت داده‌ها")
                }
                items(uiState.dataActions, key = { it.id }, span = { GridItemSpan(3) }) { action ->
                    GameCard(
                        title = action.title,
                        subtitle = action.about,
                        icon = mapIcon(action.iconRes),
                        gradientStart = Color(action.bgGradientStart),
                        gradientEnd = Color(action.bgGradientEnd),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                // Recent Games Section
                item(key = "header_recent", span = { GridItemSpan(6) }) {
                    SectionHeader(title = "آخرین بازی‌ها")
                }
                items(uiState.recentGames.take(3), key = { it.id }, span = { GridItemSpan(6) }) { game ->
                    RecentGameCard(
                        game = game,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 8.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = TextDark
    )
}

@Composable
fun QuickActionRow(actions: List<HomeItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        actions.take(4).forEach { action ->
            QuickActionButton(
                icon = mapIcon(action.iconRes),
                onClick = {}
            )
        }
    }
}

@Composable
fun RecentGameCard(game: RecentGame, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = mapIcon(game.iconRes),
                        contentDescription = null,
                        tint = OrangePrimary,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = game.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDark
                    )
                    Text(
                        text = game.status,
                        fontSize = 12.sp,
                        color = if (game.isFinished) Color.Gray else OrangePrimary
                    )
                }
            }

            if (!game.isFinished) {
                Button(
                    onClick = { },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary)
                ) {
                    Text(text = "ادامه", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Text(
                    text = game.date,
                    fontSize = 11.sp,
                    color = Color.LightGray
                )
            }
        }
    }
}

fun mapIcon(iconName: String): ImageVector {
    return when (iconName) {
        "hat" -> Icons.Default.MilitaryTech
        "eye" -> Icons.Default.Visibility
        "mask" -> Icons.Default.Face
        "mountain" -> Icons.Default.Terrain
        "group_add" -> Icons.Default.GroupAdd
        "person_add" -> Icons.Default.PersonAdd
        "actors" -> Icons.Default.RecentActors
        "abc" -> Icons.Default.Abc
        "leaderboard" -> Icons.Default.Leaderboard
        "lottery" -> Icons.Default.ConfirmationNumber
        "money" -> Icons.Default.AttachMoney
        "dice" -> Icons.Default.Casino
        "coin" -> Icons.Default.MonetizationOn
        "schedule" -> Icons.Default.Schedule
        "whatsapp" -> Icons.Default.Call
        "telegram" -> Icons.AutoMirrored.Filled.Send
        "youtube" -> Icons.Default.PlayCircle
        "upload" -> Icons.Default.Upload
        "download" -> Icons.Default.Download
        else -> Icons.Default.Extension
    }
}

@Preview(showBackground = true, device = PIXEL_6)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        uiState = HomeUiState().copy(
            games = listOf(
                HomeItem("1", "مافیا", "بازی گروهی در قالب تیمی", "hat", 0xFFE57373.toInt(), 0xFFC62828.toInt(), true),
                HomeItem("2", "جاسوس", "بازی گروهی در قالب انفرادی", "eye", 0xFF9E9E9E.toInt(), 0xFF424242.toInt(), true),
                HomeItem("3", "پانتومیم", "پر حرکت", "mask", 0xFFBA68C8.toInt(), 0xFF7B1FA2.toInt(), true),
                HomeItem("4", "تپه ندیده", "فان و سرگرم کننده", "mountain", 0xFF81C784.toInt(), 0xFF2E7D32.toInt(), true)
            ),
            banners = listOf(
                "https://news-cdn.varzesh3.com/pictures/2026/07/24/C/zsddbbtg8.webp?w=350",
                "https://news-cdn.varzesh3.com/pictures/2026/07/25/A/zqtjtoun2.webp?w=350",
                "https://news-cdn.varzesh3.com/pictures/2026/07/25/A/dojrvyfu8.webp?w=350"
            ),
            socialActions = listOf(
                HomeItem("s1", "واتس‌اپ", "", "whatsapp", 0xFF4CAF50.toInt(), 0xFF2E7D32.toInt(), true),
                HomeItem("s2", "تلگرام", "", "telegram", 0xFF2196F3.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("s3", "یوتیوب", "", "youtube", 0xFFF44336.toInt(), 0xFFD32F2F.toInt(), true)
            ),
            quickActions = listOf(
                HomeItem("q1", "ساخت تیم", "", "group_add", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q2", "ساخت بازیکن", "", "person_add", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q3", "ساخت نقش", "", "actors", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true)
            ),
            tools = listOf(
                HomeItem("t1", "جدول رده بندی", "", "leaderboard", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t2", "قرعه کشی", "", "lottery", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t3", "دونگ", "", "money", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t4", "تاس", "", "dice", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t5", "سکه", "", "coin", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t6", "نوبت دهی", "", "schedule", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true)
            ),
            dataActions = listOf(
                HomeItem("d1", "وارد کردن دیتابیس", "", "download", 0xFFBBDEFB.toInt(), 0xFF90CAF9.toInt(), true),
                HomeItem("d2", "خروجی دیتابیس", "", "upload", 0xFFFFE0B2.toInt(), 0xFFFFCC80.toInt(), true)
            ),
            recentGames = listOf(
                RecentGame("r1", "مافیا", "به اتمام رسیده", true, "hat", "۱۴۰۲/۰۵/۰۴"),
                RecentGame("r2", "پانتومیم", "نا تمام مانده", false, "mask", "۱4۰۲/۰۵/۰۳")
            )
        )
    )
}
