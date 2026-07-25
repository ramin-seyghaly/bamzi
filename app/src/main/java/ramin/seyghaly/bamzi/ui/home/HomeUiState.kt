package ramin.seyghaly.bamzi.ui.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val games: List<HomeItem> = emptyList(),
    val tools: List<HomeItem> = emptyList(),
    val quickActions: List<HomeItem> = emptyList(),
    val socialActions: List<HomeItem> = emptyList(),
    val banners: List<String> = emptyList(),
    val dataActions: List<HomeItem> = emptyList(),
    val recentGames: List<RecentGame> = emptyList()
)

data class RecentGame(
    val id: String,
    val title: String,
    val status: String,
    val isFinished: Boolean,
    val iconRes: String,
    val date: String
)

data class HomeItem(
    val id: String,
    val title: String,
    val about: String,
    val iconRes: String,
    val bgGradientStart: Int,
    val bgGradientEnd: Int,
    val enable: Boolean
)
