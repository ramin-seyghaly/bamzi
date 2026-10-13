package ramin.seyghaly.bamzi.ui.theme

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Authentic Claymorphism Modifiers: Extra faint, subtle white highlight and standard dark shadow.
 */

fun Modifier.clayCard(
    isDark: Boolean = false,
    shape: Shape,
    elevation: Dp = 18.dp
): Modifier = this
    .shadow(
        elevation = elevation,
        shape = shape,
        ambientColor = if (isDark) Color.Black.copy(alpha = 0.6f) else Color(0x2849108B),
        spotColor = if (isDark) Color(0x4049108B) else Color(0x1F49108B)
    )
    .background(
        color = if (isDark) Color(0xFF1E1038) else Color(0xFFFFFFFF),
        shape = shape
    )
    .drawBehind {
        val topLeftLight = if (isDark) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.8f)
        val bottomRightDark = if (isDark) Color.Black.copy(alpha = 0.95f) else Color(0xFF49108B)

        val cornerRadius = 42.dp.toPx()
        val steps = 8

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val lightAlpha = (1f - progress) * 0.12f // Extra faint and subtle white highlight
            val darkAlpha = (1f - progress) * 0.5f  // 50% opacity for dark shadow

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topLeftLight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
            )

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, bottomRightDark.copy(alpha = darkAlpha)),
                    start = Offset(size.width * 0.3f, size.height * 0.3f),
                    end = Offset(size.width, size.height)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
            )
        }
    }

fun Modifier.clayButtonPrimary(
    shape: Shape
): Modifier = this
    .shadow(
        elevation = 12.dp,
        shape = shape,
        ambientColor = Color(0x598D46E8),
        spotColor = Color(0x598D46E8)
    )
    .background(
        brush = Brush.linearGradient(
            colors = listOf(BamziLightPrimaryLight, BamziLightPrimary, BamziLightPrimaryDark), // #8d46e8, #7e30e1, #6220c0
            start = Offset(0f, 0f),
            end = Offset(500f, 500f)
        ),
        shape = shape
    )
    .drawBehind {
        val topHighlight = Color.White.copy(alpha = 0.8f)
        val bottomShadow = Color(0xFF1D0338)
        val cornerRadius = 22.dp.toPx()
        val steps = 8

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val lightAlpha = (1f - progress) * 0.12f // Extra faint and subtle white highlight
            val darkAlpha = (1f - progress) * 0.5f  // 50% opacity for dark shadow

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topHighlight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
            )
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, bottomShadow.copy(alpha = darkAlpha)),
                    start = Offset(size.width * 0.3f, size.height * 0.3f),
                    end = Offset(size.width, size.height)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
            )
        }
    }

fun Modifier.clayButtonSecondary(
    isDark: Boolean = false,
    shape: Shape
): Modifier = this
    .shadow(
        elevation = 8.dp,
        shape = shape,
        ambientColor = if (isDark) Color.Black.copy(alpha = 0.45f) else Color(0x1449108B),
        spotColor = if (isDark) Color(0x2649108B) else Color.White
    )
    .background(
        color = if (isDark) Color(0xFF2D1852) else Color(0xFFFFFFFF),
        shape = shape
    )
    .drawBehind {
        val topLeftLight = if (isDark) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.8f)
        val bottomShadow = if (isDark) Color.Black.copy(alpha = 0.65f) else Color(0xFF49108B)
        val cornerRadius = 22.dp.toPx()
        val steps = 8

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val lightAlpha = (1f - progress) * 0.12f // Extra faint and subtle white highlight
            val darkAlpha = (1f - progress) * 0.5f  // 50% opacity for dark shadow

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topLeftLight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
            )
            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, bottomShadow.copy(alpha = darkAlpha)),
                    start = Offset(size.width * 0.3f, size.height * 0.3f),
                    end = Offset(size.width, size.height)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.5f)
            )
        }
    }

fun Modifier.clayInset(
    isDark: Boolean = false,
    shape: Shape
): Modifier = this
    .background(
        color = if (isDark) Color(0xFF0D061A) else Color(0xFFEAF1FB),
        shape = shape
    )
    .drawBehind {
        val topDarkShadow = if (isDark) Color.Black.copy(alpha = 0.8f) else Color(0x1449108B)
        val cornerRadius = 16.dp.toPx()
        val steps = 4

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val alphaFactor = (1f - progress) * 0.5f

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topDarkShadow.copy(alpha = alphaFactor), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.6f, size.height * 0.6f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2f)
            )
        }
    }
