package ramin.seyghaly.bamzi.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            dataActions = listOf(
                HomeItem("d1", "وارد کردن دیتابیس", "", "download", 0xFFBBDEFB.toInt(), 0xFF90CAF9.toInt(), true),
                HomeItem("d2", "خروجی دیتابیس", "", "upload", 0xFFFFE0B2.toInt(), 0xFFFFCC80.toInt(), true)
            ),
            quickActions = listOf(
                HomeItem("q1", "ساخت تیم", "", "group_add", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q2", "ساخت بازیکن", "", "person_add", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q3", "ساخت نقش", "", "actors", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true),
                HomeItem("q4", "ساخت کلمه", "", "abc", 0xFF64B5F6.toInt(), 0xFF1976D2.toInt(), true)
            ),
            tools = listOf(
                HomeItem("t1", "جدول رده بندی", "", "leaderboard", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t2", "قرعه کشی", "", "lottery", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t3", "دونگ", "", "money", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t4", "تاس", "", "dice", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t5", "سکه", "", "coin", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true),
                HomeItem("t6", "نوبت دهی", "", "schedule", 0xFFFFB74D.toInt(), 0xFFF57C00.toInt(), true)
            ),
            recentGames = listOf(
                RecentGame("r1", "مافیا", "به اتمام رسیده", true, "hat", "۱۴۰۲/۰۵/۰۴"),
                RecentGame("r2", "پانتومیم", "نا تمام مانده", false, "mask", "۱۴۰۲/۰۵/۰۳"),
                RecentGame("r3", "جاسوس", "به اتمام رسیده", true, "eye", "۱۴۰۲/۰۵/۰۱")
            )
        )
    }
}
