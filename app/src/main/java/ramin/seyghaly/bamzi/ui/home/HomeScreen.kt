package ramin.seyghaly.bamzi.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.tooling.preview.Devices.PIXEL_6
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ramin.seyghaly.bamzi.R
import ramin.seyghaly.bamzi.ui.components.*
import ramin.seyghaly.bamzi.ui.theme.BamziTheme
import ramin.seyghaly.bamzi.ui.theme.OrangePrimary

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
    // Performance Optimization: Map icons and colors ONCE per state change, not during scroll
    val groupGames = remember(uiState.games) { uiState.games }
    val quickActions = remember(uiState.quickActions) { uiState.quickActions }
    val otherTools = remember(uiState.tools) { uiState.tools }
    val dataActions = remember(uiState.dataActions) { uiState.dataActions }
    val socialActions = remember(uiState.socialActions) { uiState.socialActions }
    val recentGames = remember(uiState.recentGames) { uiState.recentGames }

    Scaffold(
        topBar = { HomeHeader() },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Banner
            item(key = "banner", span = { GridItemSpan(6) }) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    HomeBanner(banners = uiState.banners)
                }
            }

            item(key = "social_actions", span = { GridItemSpan(6) }) {
                Column {
                    QuickActionRow(actions = socialActions)
                }
            }

            item(key = "header_games", span = { GridItemSpan(6) }) {
                SectionHeader(title = stringResource(R.string.group_games), showPadding = false)
            }
            items(groupGames, key = { it.id }, span = { GridItemSpan(3) }) { game ->
                val icon = remember(game.iconRes) { mapIcon(game.iconRes) }
                GameCard(
                    title = stringResource(game.titleRes),
                    subtitle = game.aboutRes?.let { stringResource(it) } ?: "",
                    icon = icon,
                    iconColor = Color(game.iconColor),
                    iconBgColor = Color(game.iconBgColor),
                    modifier = Modifier
                )
            }

            item(key = "header_settings", span = { GridItemSpan(6) }) {
                SectionHeader(title = stringResource(R.string.quick_settings), showPadding = false)
            }
            items(quickActions, key = { it.id }, span = { GridItemSpan(2) }) { action ->
                val icon = remember(action.iconRes) { mapIcon(action.iconRes) }
                ToolCard(
                    title = stringResource(action.titleRes),
                    icon = icon,
                    iconColor = Color(action.iconColor),
                    iconBgColor = Color(action.iconBgColor),
                    modifier = Modifier
                )
            }

            item(key = "header_tools", span = { GridItemSpan(6) }) {
                SectionHeader(title = stringResource(R.string.other_tools), showPadding = false)
            }
            items(otherTools, key = { it.id }, span = { GridItemSpan(2) }) { tool ->
                val icon = remember(tool.iconRes) { mapIcon(tool.iconRes) }
                ToolCard(
                    title = stringResource(tool.titleRes),
                    icon = icon,
                    iconColor = Color(tool.iconColor),
                    iconBgColor = Color(tool.iconBgColor),
                    modifier = Modifier
                )
            }

            item(key = "header_data", span = { GridItemSpan(6) }) {
                SectionHeader(title = stringResource(R.string.data_management), showPadding = false)
            }
            items(dataActions, key = { it.id }, span = { GridItemSpan(3) }) { action ->
                val icon = remember(action.iconRes) { mapIcon(action.iconRes) }
                GameCard(
                    title = stringResource(action.titleRes),
                    subtitle = action.aboutRes?.let { stringResource(it) } ?: "",
                    icon = icon,
                    iconColor = Color(action.iconColor),
                    iconBgColor = Color(action.iconBgColor),
                    modifier = Modifier
                )
            }

            item(key = "header_recent", span = { GridItemSpan(6) }) {
                SectionHeader(title = stringResource(R.string.recent_games), showPadding = false)
            }
            items(recentGames.take(3), key = { it.id }, span = { GridItemSpan(6) }) { game ->
                RecentGameCard(
                    game = game,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, showPadding: Boolean = true) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = if (showPadding) 16.dp else 0.dp,
                end = if (showPadding) 16.dp else 0.dp,
                top = 24.dp,
                bottom = 12.dp
            ),
        fontSize = 22.sp,
        fontWeight = FontWeight.ExtraBold,
        color = MaterialTheme.colorScheme.onBackground,
        textAlign = TextAlign.Start // Will be Right in RTL
    )
}

@Composable
fun QuickActionRow(actions: List<HomeItem>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        actions.forEach { action ->
            val icon = remember(action.iconRes) { mapIcon(action.iconRes) }
            QuickActionButton(
                icon = icon,
                contentColor = Color(action.iconColor),
                onClick = {},
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun RecentGameCard(game: RecentGame, modifier: Modifier = Modifier) {
    val isProgress = !game.isFinished
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isProgress) 4.dp else 1.dp),
        border = BorderStroke(
            width = if (isProgress) 2.dp else 1.dp,
            color = if (isProgress) Color(0xFF0056B3) else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val iconColor = if (game.iconRes == "mask") Color(0xFF4A90E2) else Color(0xFFE57373)
                val iconBg = if (game.iconRes == "mask") Color(0xFFE3F2FD) else Color(0xFFFFEBEE)
                
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(iconBg),
                    contentAlignment = Alignment.Center
                ) {
                    val icon = remember(game.iconRes) { mapIcon(game.iconRes) }
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = stringResource(game.titleRes),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = stringResource(game.statusRes),
                        fontSize = 12.sp,
                        color = if (game.isFinished) MaterialTheme.colorScheme.onSurfaceVariant else OrangePrimary
                    )
                }
            }

            if (isProgress) {
                Button(
                    onClick = { },
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0056B3))
                ) {
                    Text(text = stringResource(R.string.continue_game), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Text(
                    text = game.date,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
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
        "truth_dare" -> Icons.Default.QuestionAnswer
        "whatsapp" -> Icons.Default.Call
        "telegram" -> Icons.AutoMirrored.Filled.Send
        "youtube" -> Icons.Default.PlayCircle
        "upload" -> Icons.Default.Upload
        "download" -> Icons.Default.Download
        else -> Icons.Default.Extension
    }
}

@Preview(showBackground = true, device = PIXEL_6, locale = "fa")
@Composable
fun HomeScreenFaPreview() {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        BamziTheme {
        HomeScreenContent(
            uiState = HomeUiState().copy(
                games = listOf(
                    HomeItem("1", R.string.mafia, R.string.mafia_about, "hat", 0xFFE57373L, 0xFFFFEBEEL, true),
                    HomeItem("2", R.string.spy, R.string.spy_about, "eye", 0xFF757575L, 0xFFF5F5F5L, true),
                    HomeItem("5", R.string.truth_dare, R.string.truth_dare_about, "truth_dare", 0xFFFF8A65L, 0xFFFFF3E0L, true)
                ),
                banners = listOf(""),
                socialActions = listOf(
                    HomeItem("s1", R.string.whatsapp, null, "whatsapp", 0xFF757575L, 0xFFF5F5F5L, true)
                ),
                quickActions = listOf(
                    HomeItem("q1", R.string.team_creation, null, "group_add", 0xFF4A90E2L, 0xFFE3F2FDL, true)
                ),
                tools = listOf(
                    HomeItem("t1", R.string.leaderboard, null, "leaderboard", 0xFFFF8A65L, 0xFFFFF3E0L, true)
                ),
                dataActions = listOf(
                    HomeItem("d1", R.string.import_db, null, "download", 0xFF4A90E2L, 0xFFE3F2FDL, true)
                ),
                recentGames = listOf(
                    RecentGame("r1", R.string.mafia, R.string.finished, true, "hat", "۱۴۰۲/۰۵/۰۴")
                )
            )
        )
    }
}
}

@Preview(showBackground = true, device = PIXEL_6, locale = "en", uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenEnDarkPreview() {
    BamziTheme(darkTheme = true) {
        HomeScreenContent(
            uiState = HomeUiState().copy(
                games = listOf(
                    HomeItem("1", R.string.mafia, R.string.mafia_about, "hat", 0xFFE57373L, 0xFFFFEBEEL, true),
                    HomeItem("2", R.string.spy, R.string.spy_about, "eye", 0xFF757575L, 0xFFF5F5F5L, true),
                    HomeItem("5", R.string.truth_dare, R.string.truth_dare_about, "truth_dare", 0xFFFF8A65L, 0xFFFFF3E0L, true)
                ),
                banners = listOf(""),
                socialActions = listOf(
                    HomeItem("s1", R.string.whatsapp, null, "whatsapp", 0xFF757575L, 0xFFF5F5F5L, true)
                ),
                quickActions = listOf(
                    HomeItem("q1", R.string.team_creation, null, "group_add", 0xFF4A90E2L, 0xFFE3F2FDL, true)
                ),
                tools = listOf(
                    HomeItem("t1", R.string.leaderboard, null, "leaderboard", 0xFFFF8A65L, 0xFFFFF3E0L, true)
                ),
                dataActions = listOf(
                    HomeItem("d1", R.string.import_db, null, "download", 0xFF4A90E2L, 0xFFE3F2FDL, true)
                ),
                recentGames = listOf(
                    RecentGame("r1", R.string.mafia, R.string.finished, true, "hat", "2024/05/04")
                )
            )
        )
    }
}
