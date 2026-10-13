package ramin.seyghaly.bamzi.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ramin.seyghaly.bamzi.R
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _uiState.value = HomeUiState(
            games = listOf(
                HomeItem("1", R.string.mafia, R.string.mafia_about, "hat", 0xFFE57373L, 0xFFFFEBEEL, true),
                HomeItem("2", R.string.spy, R.string.spy_about, "eye", 0xFF757575L, 0xFFF5F5F5L, true),
                HomeItem("3", R.string.mountain, R.string.mountain_about, "mountain", 0xFF9575CDL, 0xFFF3E5F5L, true),
                HomeItem("4", R.string.pantomime, R.string.pantomime_about, "mask", 0xFF4A90E2L, 0xFFE3F2FDL, true),
                HomeItem("5", R.string.truth_dare, R.string.truth_dare_about, "truth_dare", 0xFFFF8A65L, 0xFFFFF3E0L, true)
            ),
            banners = listOf(
                "https://news-cdn.varzesh3.com/pictures/2026/07/24/C/zsddbbtg8.webp?w=350",
                "https://news-cdn.varzesh3.com/pictures/2026/07/25/A/zqtjtoun2.webp?w=350",
                "https://news-cdn.varzesh3.com/pictures/2026/07/25/A/dojrvyfu8.webp?w=350"
            ),
            socialActions = listOf(
                HomeItem("s1", R.string.whatsapp, null, "whatsapp", 0xFF757575L, 0xFFF5F5F5L, true),
                HomeItem("s2", R.string.telegram, null, "telegram", 0xFF4A90E2L, 0xFFE3F2FDL, true),
                HomeItem("s3", R.string.youtube, null, "youtube", 0xFFE57373L, 0xFFFFEBEEL, true)
            ),
            dataActions = listOf(
                HomeItem("d1", R.string.import_db, null, "download", 0xFF4A90E2L, 0xFFE3F2FDL, true),
                HomeItem("d2", R.string.export_db, null, "upload", 0xFF4A90E2L, 0xFFE3F2FDL, true)
            ),
            quickActions = listOf(
                HomeItem("q1", R.string.team_creation, null, "group_add", 0xFF4A90E2L, 0xFFE3F2FDL, true),
                HomeItem("q2", R.string.player_creation, null, "person_add", 0xFF4A90E2L, 0xFFE3F2FDL, true),
                HomeItem("q3", R.string.role_creation, null, "actors", 0xFF4A90E2L, 0xFFE3F2FDL, true),
                HomeItem("q4", R.string.word_creation, null, "abc", 0xFF4A90E2L, 0xFFE3F2FDL, true)
            ),
            tools = listOf(
                HomeItem("t1", R.string.leaderboard, null, "leaderboard", 0xFFFF8A65L, 0xFFFFF3E0L, true),
                HomeItem("t2", R.string.lottery, null, "lottery", 0xFFFF8A65L, 0xFFFFF3E0L, true),
                HomeItem("t3", R.string.dong, null, "money", 0xFFFF8A65L, 0xFFFFF3E0L, true),
                HomeItem("t4", R.string.dice, null, "dice", 0xFFFF8A65L, 0xFFFFF3E0L, true),
                HomeItem("t5", R.string.coin, null, "coin", 0xFFFF8A65L, 0xFFFFF3E0L, true),
                HomeItem("t6", R.string.turn_scheduling, null, "schedule", 0xFFFF8A65L, 0xFFFFF3E0L, true)
            ),
            recentGames = listOf(
                RecentGame("r1", R.string.mafia, R.string.finished, true, "hat", "۱۴۰۲/۰۵/۰۴"),
                RecentGame("r2", R.string.pantomime, R.string.game_in_progress, false, "mask", "۱۴۰۲/۰۵/۰۳"),
                RecentGame("r3", R.string.spy, R.string.finished, true, "eye", "۱۴۰۲/۰۵/۰۱")
            )
        )
    }
}
