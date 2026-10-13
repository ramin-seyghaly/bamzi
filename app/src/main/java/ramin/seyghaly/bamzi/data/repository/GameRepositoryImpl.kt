package ramin.seyghaly.bamzi.data.repository

import ramin.seyghaly.bamzi.R
import ramin.seyghaly.bamzi.domain.model.Game
import ramin.seyghaly.bamzi.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor() : GameRepository {
    override suspend fun getOfflineGames(): List<Game> {
        return listOf(
            Game(
                id = "mafia",
                title = "مدیریت مافیا",
                category = "نقش‌آفرینی",
                description = "سیستم هوشمند مدیریت بازی مافیا، توزیع نقش‌ها، مدیریت روز و ثبت آرا",
                iconEmoji = "🎭",
                coverResId = R.drawable.mafia,
                tags = listOf("گروهی", "استراتژیک", "پرطرفدار"),
                genre = "نقش‌آفرینی",
                isActive = true
            ),
            Game(
                id = "pantomime",
                title = "پانتومیم (اجرای گنگ)",
                category = "مهارتی",
                description = "بانک کلمات پیشرفته، تایمر صوتی و تصویری و مدیریت امتیازات تیم‌ها",
                iconEmoji = "🎬",
                coverResId = R.drawable.pantomime,
                tags = listOf("عصرانه", "خنده‌دار", "تیمی"),
                genre = "پانتومیم",
                isActive = true
            ),
            Game(
                id = "spy",
                title = "مدیریت بازی جاسوس",
                category = "فکری و استدلال",
                description = "یافتن جاسوس در میان بازیکنان با پرسش و پاسخ‌های هوشمندانه و هدایت بازی",
                iconEmoji = "🕵️‍♂️",
                coverResId = R.drawable.mafia,
                tags = listOf("پنهان", "معمایی", "فکری"),
                genre = "استدلالی",
                isActive = true
            ),
            Game(
                id = "tappeh",
                title = "تپه ندیده",
                category = "دورهمی",
                description = "بازی جذاب و پرهیجان چالش‌های خنده‌دار و حدس کلمات در جمع دوستان",
                iconEmoji = "🏔️",
                coverResId = R.drawable.pantomime,
                tags = listOf("پرهیجان", "دورهمی", "مهمانی"),
                genre = "سرگرمی",
                isActive = false
            ),
            Game(
                id = "truth",
                title = "جرات و حقیقت",
                category = "هیجانی",
                description = "سوالات چالش‌برانگیز و جرات‌های دیوانه‌وار برای داغ کردن جمع دوستانه",
                iconEmoji = "🎯",
                coverResId = R.drawable.mafia,
                tags = listOf("هیجانی", "صمیمی", "چالش"),
                genre = "هیجانی",
                isActive = true
            )
        )
    }
}
