package ramin.seyghaly.bamzi.domain.model

data class Game(
    val id: String = "",
    val title: String = "",
    val category: String = "",
    val description: String = "",
    val iconEmoji: String = "🎮",
    val coverResId: Int = 0,
    val tags: List<String> = emptyList(),
    val genre: String = "",
    val isActive: Boolean = true
)
