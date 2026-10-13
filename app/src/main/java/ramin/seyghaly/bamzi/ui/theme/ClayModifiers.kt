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
 * Perfectly balanced Claymorphism Modifiers:
 * - Top & Left = Soft light source (topLeftLight)
 * - Right & Bottom = Subtle shadow (bottomRightDark)
 * - Light Mode: Soft and subtle 20% opacity highlights.
 */

fun Modifier.clayCard(
    isDark: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape,
    elevation: Dp = 28.dp
): Modifier = this
    .shadow(
        elevation = elevation,
        shape = shape,
        ambientColor = if (isDark) Color.Black.copy(alpha = 0.95f) else Color(0xFF372C7F).copy(alpha = 0.15f),
        spotColor = if (isDark) Color(0xFF433892).copy(alpha = 0.35f) else Color(0xFF5E52B8).copy(alpha = 0.08f)
    )
    .background(
        brush = if (isDark) {
            Brush.linearGradient(
                colors = listOf(Color(0xFF372C7F), Color(0xFF1D1A45)),
                start = Offset(0f, 0f),
                end = Offset(1000f, 1000f)
            )
        } else {
            Brush.linearGradient(
                colors = listOf(Color(0xFFFFFFFF), Color(0xFFF4F0FF)),
                start = Offset(0f, 0f),
                end = Offset(1000f, 1000f)
            )
        },
        shape = shape
    )
    .drawBehind {
        // Top & Left = Soft light (20% opacity in light mode), Right & Bottom = Subtle shadow
        val topLeftLight = if (isDark) Color(0xFFD4BCE0).copy(alpha = 0.35f) else Color(0xFF685CC7).copy(alpha = 0.2f)
        val bottomRightDark = if (isDark) Color.Black.copy(alpha = 0.7f) else Color(0xFF372C7F).copy(alpha = 0.06f)

        val cornerRadius = 28.dp.toPx()
        val steps = 5

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val lightAlpha = (1f - progress) * (if (isDark) 0.5f else 0.1f)
            val darkAlpha = (1f - progress) * (if (isDark) 0.5f else 0.15f)

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topLeftLight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2f)
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
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2f)
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
        ambientColor = if (isDark) Color(0xFFD4BCE0).copy(alpha = 0.8f) else Color(0xFF372C7F).copy(alpha = 0.25f),
        spotColor = if (isDark) Color(0xFFD4BCE0).copy(alpha = 0.7f) else Color(0xFF5E52B8).copy(alpha = 0.15f)
    )
    .background(
        brush = if (isDark) {
            Brush.linearGradient(
                colors = listOf(Color(0xFFD4BCE0), Color(0xFFB597C4), Color(0xFF4E489E)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        } else {
            Brush.linearGradient(
                colors = listOf(Color(0xFF685CC7), Color(0xFF5E52B8)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        },
        shape = shape
    )
    .drawBehind {
        val topHighlight = if (isDark) Color.White.copy(alpha = 0.75f) else Color(0xFFD4BCE0).copy(alpha = 0.3f)
        val bottomShadow = if (isDark) Color(0xFF1D1A45).copy(alpha = 0.8f) else Color(0xFF1D1A45).copy(alpha = 0.2f)
        val cornerRadius = 22.dp.toPx()
        val steps = 5

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val lightAlpha = (1f - progress) * 0.3f
            val darkAlpha = (1f - progress) * 0.2f

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topHighlight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2f)
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
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2f)
            )
        }
    }

fun Modifier.clayButtonSecondary(
    isDark: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape
): Modifier = this
    .shadow(
        elevation = 20.dp,
        shape = shape,
        ambientColor = if (isDark) Color.Black.copy(alpha = 0.85f) else Color(0xFF372C7F).copy(alpha = 0.22f),
        spotColor = if (isDark) Color(0xFF372C7F).copy(alpha = 0.5f) else Color(0xFF5E52B8).copy(alpha = 0.02f)
    )
    .background(
        brush = if (isDark) {
            Brush.linearGradient(
                colors = listOf(Color(0xFF433892), Color(0xFF372C7F)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        } else {
            Brush.linearGradient(
                colors = listOf(Color(0xFFFFFFFF), Color(0xFFF9F6FF)),
                start = Offset(0f, 0f),
                end = Offset(500f, 500f)
            )
        },
        shape = shape
    )
    .drawBehind {
        val topLeftLight = if (isDark) Color(0xFFD4BCE0).copy(alpha = 0.25f) else Color(0xFF685CC7).copy(alpha = 0.2f)
        val bottomShadow = if (isDark) Color.Black.copy(alpha = 0.6f) else Color(0xFF372C7F).copy(alpha = 0.06f)
        val cornerRadius = 28.dp.toPx()
        val steps = 5

        for (i in 0 until steps) {
            val progress = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val lightAlpha = (1f - progress) * 0.3f
            val darkAlpha = (1f - progress) * 0.15f

            drawRoundRect(
                brush = Brush.linearGradient(
                    colors = listOf(topLeftLight.copy(alpha = lightAlpha), Color.Transparent),
                    start = Offset(0f, 0f),
                    end = Offset(size.width * 0.7f, size.height * 0.7f)
                ),
                topLeft = Offset(insetOffset, insetOffset),
                size = Size(size.width - insetOffset * 2, size.height - insetOffset * 2),
                cornerRadius = CornerRadius(maxOf(0f, cornerRadius - insetOffset)),
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2f)
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
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.2f)
            )
        }
    }

fun Modifier.clayInset(
    isDark: Boolean = false,
    shape: androidx.compose.ui.graphics.Shape
): Modifier = this
    .background(
        color = if (isDark) Color(0xFF18153D) else Color(0xFFE8E2FF),
        shape = shape
    )
    .drawBehind {
        val topDarkShadow = if (isDark) Color.Black.copy(alpha = 0.85f) else Color(0xFF372C7F).copy(alpha = 0.12f)
        val cornerRadius = 16.dp.toPx()
        val steps = 4

        for (i in 0 until steps) {
            val progressVal = i.toFloat() / steps
            val insetOffset = i.toFloat()
            val alphaFactor = (1f - progressVal) * (if (isDark) 0.65f else 0.2f)

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
