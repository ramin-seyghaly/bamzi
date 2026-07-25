package ramin.seyghaly.bamzi.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.NEXUS_9
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item { HomeBanner(banners = uiState.banners) }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    QuickActionRow(actions = uiState.socialActions)
                }

                item {
                    SectionHeader(title = "بازی‌های گروهی")
                    GamesGrid(items = uiState.games)
                }

                item {
                    SectionHeader(title = "تنظیمات سریع بازی")
                    ToolsGrid(items = uiState.quickActions)
                }

                item {
                    SectionHeader(title = "ابزارهای دیگر")
                    ToolsGrid(items = uiState.tools)
                }

                item {
                    SectionHeader(title = "مدیریت داده‌ها")
                    DataManagementSection(items = uiState.dataActions)
                }

                item {
                    SectionHeader(title = "آخرین بازی‌ها")
                    RecentGamesSection(games = uiState.recentGames)
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
fun GamesGrid(items: List<HomeItem>) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowItems.forEach { item ->
                    GameCard(
                        title = item.title,
                        subtitle = item.about,
                        icon = mapIcon(item.iconRes),
                        gradientStart = Color(item.bgGradientStart),
                        gradientEnd = Color(item.bgGradientEnd),
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ToolsGrid(items: List<HomeItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        items.chunked(3).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                rowItems.forEach { item ->
                    ToolCard(
                        title = item.title,
                        icon = mapIcon(item.iconRes),
                        gradientStart = Color(item.bgGradientStart),
                        gradientEnd = Color(item.bgGradientEnd),
                        modifier = Modifier.weight(1f)
                    )
                }
                
                // Add empty spacers if row is not full
                repeat(3 - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
fun DataManagementSection(items: List<HomeItem>) {
    GamesGrid(items = items)
}

@Composable
fun RecentGamesSection(games: List<RecentGame>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        games.take(3).forEach { game ->
            RecentGameCard(game = game)
        }
    }
}

@Composable
fun RecentGameCard(game: RecentGame) {
    Card(
        modifier = Modifier.fillMaxWidth(),
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
                RecentGame("r2", "پانتومیم", "نا تمام مانده", false, "mask", "۱۴۰۲/۰۵/۰۳")
            )
        )
    )
}
