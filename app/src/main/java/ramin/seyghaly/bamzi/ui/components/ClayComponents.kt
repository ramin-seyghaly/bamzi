package ramin.seyghaly.bamzi.ui.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramin.seyghaly.bamzi.ui.theme.BamziLightBorder
import ramin.seyghaly.bamzi.ui.theme.BamziSuccess
import ramin.seyghaly.bamzi.ui.theme.clayButtonPrimary
import ramin.seyghaly.bamzi.ui.theme.clayButtonSecondary
import ramin.seyghaly.bamzi.ui.theme.clayCard
import ramin.seyghaly.bamzi.ui.theme.clayInset

/**
 * Bamzi Design System - Reusable Atomic Claymorphism Components
 */

// Bouncy touch scale animation
fun Modifier.clayClickable(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    var isPressed by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMediumLow
        ),
        label = "clayPressScale"
    )

    this
        .graphicsLayer(scaleX = scale, scaleY = scale)
        .pointerInput(enabled) {
            if (!enabled) return@pointerInput
            detectTapGestures(
                onPress = {
                    isPressed = true
                    try {
                        awaitRelease()
                    } finally {
                        isPressed = false
                    }
                },
                onTap = { onClick() }
            )
        }
}

@Composable
fun ClayCard(
    modifier: Modifier = Modifier,
    isDark: Boolean = false,
    shape: Shape = RoundedCornerShape(28.dp),
    elevation: Dp = 16.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    val clickModifier = if (onClick != null) {
        Modifier.clayClickable(onClick = onClick)
    } else {
        Modifier
    }

    Box(
        modifier = modifier
            .clip(shape)
            .clayCard(isDark = isDark, shape = shape, elevation = elevation)
            .then(clickModifier)
            .padding(20.dp)
    ) {
        Column(content = { content() })
    }
}

enum class ClayButtonSize {
    Small, Medium, Large
}

@Composable
fun ClayPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: ClayButtonSize = ClayButtonSize.Medium,
    isDark: Boolean = false,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    enabled: Boolean = true
) {
    val shape = RoundedCornerShape(if (size == ClayButtonSize.Small) 28.dp else 28.dp)
    val verticalPadding = when (size) {
        ClayButtonSize.Small -> 6.dp
        ClayButtonSize.Medium -> 10.dp
        ClayButtonSize.Large -> 14.dp
    }
    val horizontalPadding = when (size) {
        ClayButtonSize.Small -> 14.dp
        ClayButtonSize.Medium -> 20.dp
        ClayButtonSize.Large -> 28.dp
    }
    val fontSize = when (size) {
        ClayButtonSize.Small -> 12.5.sp
        ClayButtonSize.Medium -> 14.5.sp
        ClayButtonSize.Large -> 16.sp
    }

    Box(
        modifier = modifier
            .clip(shape)
            .clayButtonPrimary(isDark = isDark, shape = shape)
            .clayClickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                color = Color.White,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false
            )
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Icon(imageVector = trailingIcon, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
fun ClaySecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean = false,
    size: ClayButtonSize = ClayButtonSize.Medium,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    enabled: Boolean = true
) {
    val shape = RoundedCornerShape(if (size == ClayButtonSize.Small) 28.dp else 28.dp)
    val verticalPadding = when (size) {
        ClayButtonSize.Small -> 6.dp
        ClayButtonSize.Medium -> 10.dp
        ClayButtonSize.Large -> 14.dp
    }
    val horizontalPadding = when (size) {
        ClayButtonSize.Small -> 14.dp
        ClayButtonSize.Medium -> 20.dp
        ClayButtonSize.Large -> 28.dp
    }
    val fontSize = when (size) {
        ClayButtonSize.Small -> 12.5.sp
        ClayButtonSize.Medium -> 14.5.sp
        ClayButtonSize.Large -> 16.sp
    }
    val textColor = if (isDark) Color(0xFFF3F8FF) else Color(0xFF1D1338)

    Box(
        modifier = modifier
            .shadow(elevation = 10.dp, shape = shape)
            .clip(shape)
            .clayButtonSecondary(isDark = isDark, shape = shape)
            .clayClickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Icon(imageVector = leadingIcon, contentDescription = null, tint = textColor, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(6.dp))
            }
            Text(
                text = text,
                color = textColor,
                fontSize = fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false
            )
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Icon(imageVector = trailingIcon, contentDescription = null, tint = textColor, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
fun ClayInsetBox(
    modifier: Modifier = Modifier,
    isDark: Boolean = false,
    shape: Shape = RoundedCornerShape(28.dp),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = 10.dp, shape = shape)
            .clip(shape)
            .clayInset(isDark = isDark, shape = shape)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        content()
    }
}

@Composable
fun ClayTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isDark: Boolean = false,
    leadingIcon: ImageVector? = null
) {
    val shape = RoundedCornerShape(28.dp)
    val textColor = if (isDark) Color(0xFFF3F8FF) else Color(0xFF1D1338)
    val placeholderColor = if (isDark) Color(0xFF7E749C) else Color(0xFF958BB0)

    Box(
        modifier = modifier
            .shadow(elevation = 10.dp, shape = shape)
            .fillMaxWidth()
            .clip(shape)
            .clayInset(isDark = isDark, shape = shape)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (leadingIcon != null) {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = placeholderColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
            Box(modifier = Modifier.weight(1f)) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = placeholderColor,
                        fontSize = 14.5.sp
                    )
                }
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = LocalTextStyle.current.copy(
                        color = textColor,
                        fontSize = 14.5.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun OnlineBadge(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulseTransition")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
    )

    Row(
        modifier = modifier
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(BamziSuccess.copy(alpha = 0.15f), BamziSuccess.copy(alpha = 0.25f))
                )
            )
            .border(width = 1.dp, color = BamziSuccess.copy(alpha = 0.3f), shape = CircleShape)
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .graphicsLayer(alpha = pulseAlpha)
                .clip(CircleShape)
                .background(BamziSuccess)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "آنلاین",
            color = BamziSuccess,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TagBadge(
    text: String,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = true
) {
    val textColor = if (isPrimary) MaterialTheme.colorScheme.primary else Color(0xFFF59E0B)
    val alphaColor = textColor.copy(alpha = 0.15f)

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(alphaColor)
            .border(width = 1.dp, color = textColor.copy(alpha = 0.25f), shape = CircleShape)
            .padding(horizontal = 12.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ClayAvatar(
    modifier: Modifier = Modifier,
    size: Dp = 52.dp,
    initials: String = "ب"
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFFFFAFF), Color(0xFFEAF1FB))
                )
            )
            .border(width = 2.dp, color = Color.White, shape = CircleShape)
            .drawBehind {
                // Outer subtle glow ring
                drawCircle(
                    color = Color(0x40433D8B),
                    radius = size.toPx() * 0.5f,
                    center = center
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            fontSize = (size.value * 0.4f).sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun ClayStatItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    isDark: Boolean = false
) {
    val shape = RoundedCornerShape(28.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .clayInset(isDark = isDark, shape = shape)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = if (isDark) Color(0xFFABA1C7) else Color(0xFF64567F)
            )
        }
    }
}
