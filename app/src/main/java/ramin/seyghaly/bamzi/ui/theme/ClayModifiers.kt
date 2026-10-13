package ramin.seyghaly.bamzi.ui.theme

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Professional Claymorphism Modifiers with bolder, thicker inner shadows
 * in both Light and Dark modes for an unmistakable 3D clay depth effect.
 */

fun Modifier.clayCard(
    isDark: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape,
    elevation: Dp = 28.dp
): Modifier = this
    .shadow(
        elevation = elevation,
        shape = shape,
        ambientColor = if (isDark) Color.Black.copy(alpha = 0.95f) else Color(0xFF455A9E).copy(alpha = 0.22f),
        spotColor = if (isDark) Color(0xFF566DB8).copy(alpha = 0.85f) else Color(0xFF203370).copy(alpha = 0.15f)
    )
    .background(
        brush = if (isDark) {
            Brush.linearGradient(
                colors = listOf(Color(0xFF455A9E), Color(0xFF2B376C)),
                start = Offset(0f, 0f),
                end = Offset(1000f, 1000f)
            )
        } else {
            Brush.linearGradient(
                colors = listOf(Color(0xFFFFFFFF), Color(0xFFB0C8EC)),
                start = Offset(0f, 0f),
                end = Offset(1000f, 1000f)
            )
        },
        shape = shape
    )
    .drawBehind {
        val topLeftLight = if (isDark) Color(0xFF374782) else Color(0xC3C4D8F0)
        val bottomRightDark = if (isDark) Color(0xE02B376C) else Color(0xFF88A2BC)

        val cornerRadius = 28.dp.toPx()
        val steps = 6 // Bolder steps

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat() * 1.2f
            val lightAlpha = (1f - progress) * (if (isDark) 0.6f else 0.75f)
            val darkAlpha = (1f - progress) * (if (isDark) 0.6f else 0.75f)

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topLeftLight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 5.0f) // Thicker stroke
            )

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, bottomRightDark.copy(alpha = darkAlpha)),
                    start = Offset(size.width * 0.3f, size.height * 0.5f),
                    end = Offset(size.width, size.height)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 5.0f) // Thicker stroke
            )
        }
    }

fun Modifier.clayButtonPrimary(
    isDark: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape
): Modifier = this
    .shadow(
        elevation = 16.dp,
        shape = shape,
        ambientColor = if (isDark) Color(0xFFD4E3FF).copy(alpha = 0.8f) else Color(0xFF455A9E).copy(alpha = 0.3f),
        spotColor = if (isDark) Color(0xFFD4E3FF).copy(alpha = 0.7f) else Color(0xFF203370).copy(alpha = 0.2f)
    )
    .background(
        brush = if (isDark) {
            Brush.linearGradient(
                colors = listOf(Color(0xFFD4E3FF), Color(0xFFACC6E0), Color(0xFF566DB8)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        } else {
            Brush.linearGradient(
                colors = listOf(Color(0xFF2A3E8A), Color(0xFF203370)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        },
        shape = shape
    )
    .drawBehind {
        val topHighlight = if (isDark) Color.White else Color.White
        val bottomShadow = if (isDark) Color(0xFF2B376C) else Color(0xFF455A9E)
        val cornerRadius = 28.dp.toPx()
        val steps = 6

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat() * 1.2f
            val lightAlpha = (1f - progress) * (if (isDark) 0.6f else 0.6f)
            val darkAlpha = (1f - progress) * (if (isDark) 0.6f else 0.5f)

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topHighlight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.0f)
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
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.0f)
            )
        }
    }

fun Modifier.clayButtonSecondary(
    isDark: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape
): Modifier = this
    .shadow(
        elevation = 10.dp,
        shape = shape,
        ambientColor = if (isDark) Color.Black.copy(alpha = 0.85f) else Color(0xFF455A9E).copy(alpha = 0.08f),
        spotColor = if (isDark) Color(0xFF455A9E).copy(alpha = 0.7f) else Color(0xFF203370).copy(alpha = 0.1f)
    )
    .background(
        brush = if (isDark) {
            Brush.linearGradient(
                colors = listOf(Color(0xFF566DB8), Color(0xFF455A9E)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        } else {
            Brush.linearGradient(
                colors = listOf(Color(0xFFFFFFFF), Color(0xFFBFD4F0)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        },
        shape = shape
    )
    .drawBehind {
        val topLeftLight = if (isDark) Color(0xFFD4E3FF) else Color.White
        val bottomShadow = if (isDark) Color.Black else Color(0xFF455A9E)
        val cornerRadius = 22.dp.toPx()
        val steps = 6

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat() * 1.2f
            val lightAlpha = (1f - progress) * (if (isDark) 0.6f else 0.7f)
            val darkAlpha = (1f - progress) * (if (isDark) 0.6f else 0.35f)

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topLeftLight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.0f)
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
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.0f)
            )
        }
    }

fun Modifier.clayInset(
    isDark: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape
): Modifier = this
    .background(
        color = if (isDark) Color(0xFF222B5C) else Color(0xFF9EC2EE),
        shape = shape
    )
    .drawBehind {
        val topDarkShadow = if (isDark) Color.Black.copy(alpha = 0.85f) else Color(0xFF455A9E).copy(alpha = 0.25f)
        val cornerRadius = 16.dp.toPx()
        val steps = 5

        for (i: Int in 0 until steps) {
            val progressVal = i.toFloat() / steps
            val insetOffset = i.toFloat() * 1.2f
            val alphaFactor = (1f - progressVal) * (if (isDark) 0.75f else 0.5f)

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topDarkShadow.copy(alpha = alphaFactor), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.6f, size.height * 0.6f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.0f)
            )
        }
    }
