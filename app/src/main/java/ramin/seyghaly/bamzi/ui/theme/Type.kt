package ramin.seyghaly.bamzi.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ramin.seyghaly.bamzi.R

// Custom IRANSansX font family from res/font
val IranSansFontFamily = FontFamily(
    Font(R.font.iransansx_thin, FontWeight.Thin),
    Font(R.font.iransansx_ultralight, FontWeight.ExtraLight),
    Font(R.font.iransansx_light, FontWeight.Light),
    Font(R.font.iransansx_regular, FontWeight.Normal),
    Font(R.font.iransansx_medium, FontWeight.Medium),
    Font(R.font.iransansx_demibold, FontWeight.SemiBold),
    Font(R.font.iransansx_bold, FontWeight.Bold),
    Font(R.font.iransansx_extrabold, FontWeight.ExtraBold),
    Font(R.font.iransansx_black, FontWeight.Black)
)

// Much stronger, deeper claymorphic text shadow
val clayTextShadow = Shadow(
    color = Color.Black.copy(alpha = 0.6f),
    offset = Offset(0f, 4f),
    blurRadius = 7f
)

// Set of Material typography styles using IRANSansX and deep 3D shadows
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        shadow = clayTextShadow
    ),
    bodyMedium = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        shadow = clayTextShadow
    ),
    bodySmall = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp,
        shadow = clayTextShadow
    ),
    titleLarge = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.0.sp,
        shadow = clayTextShadow
    ),
    titleMedium = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp,
        shadow = clayTextShadow
    ),
    titleSmall = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        shadow = clayTextShadow
    ),
    headlineLarge = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.0.sp,
        shadow = clayTextShadow
    ),
    labelLarge = TextStyle(
        fontFamily = IranSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp,
        shadow = clayTextShadow
    )
)
