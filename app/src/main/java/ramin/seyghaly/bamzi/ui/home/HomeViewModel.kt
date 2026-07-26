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
                HomeItem("1", R.string.mafia, R.string.mafia_about, "hat", 0xFFE57373.toInt(), 0xFFC62828.toInt(), true),
                HomeItem("2", R.string.spy, R.string.spy_about, "eye", 0xFF9E9E9E.toInt(), 0xFF424242.toInt(), true),
                HomeItem("3", R.string.pantomime, R.string.pantomime_about, "mask", 0xFFBA68C8.toInt(), 0xFF7B1FA2.toInt(), true),
                HomeItem("4", R.string.mountain, R.string.mountain_about, "mountain", 0xFF81C784.toInt(), 0xFF2E7D32.toInt(), true)
            ),
            banners = listOf(
                "https://news-cdn.varzesh3.com/pictures/2026/07/24/C/zsddbbtg8.webp?w=350",
                "https://news-cdn.varzesh3.com/pictures/2026/07/25/A/zqtjtoun2.webp?w=350",
                "https://news-cdn.varzesh3.com/pictures/2026/07/25/A/dojrvyfu8.webp?w=350"
            ),
            socialActions = listOf(
                HomeItem("s1", R.string.whatsapp, null, "whatsapp", 0xFF4CAF50.toInt(), 0xFF2E7D32.toInt(), true),
                HomeItem("s2", R.string.telegram, null, "telegram", 0xFF2196F3.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("s3", R.string.youtube, null, "youtube", 0xFFF44336.toInt(), 0xFFD32F2F.toInt(), true)
            ),
            dataActions = listOf(
                HomeItem("d1", R.string.import_db, null, "download", 0xFFBBDEFB.toInt(), 0xFF90CAF9.toInt(), true),
                HomeItem("d2", R.string.export_db, null, "upload", 0xFFFFE0B2.toInt(), 0xFFFFCC80.toInt(), true)
            ),
            quickActions = listOf(
                HomeItem("q1", R.string.team_creation, null, "group_add", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q2", R.string.player_creation, null, "person_add", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q3", R.string.role_creation, null, "actors", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q4", R.string.word_creation, null, "abc", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true)
            ),
            tools = listOf(
                HomeItem("t1", R.string.leaderboard, null, "leaderboard", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t2", R.string.lottery, null, "lottery", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t3", R.string.dong, null, "money", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t4", R.string.dice, null, "dice", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t5", R.string.coin, null, "coin", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t6", R.string.turn_scheduling, null, "schedule", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true)
            ),
            recentGames = listOf(
                RecentGame("r1", R.string.mafia, R.string.finished, true, "hat", "۱۴۰۲/۰۵/۰۴"),
                RecentGame("r2", R.string.pantomime, R.string.game_in_progress, false, "mask", "۱۴۰۲/۰۵/۰۳"),
                RecentGame("r3", R.string.spy, R.string.finished, true, "eye", "۱۴۰۲/۰۵/۰۱")
            )
        )
    }
}
