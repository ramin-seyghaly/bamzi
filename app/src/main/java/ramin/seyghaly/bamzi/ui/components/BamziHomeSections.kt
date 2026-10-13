package ramin.seyghaly.bamzi.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramin.seyghaly.bamzi.ui.theme.clayCard
import ramin.seyghaly.bamzi.ui.theme.clayInset

data class GameItem(
    val title: String,
    val category: String,
    val description: String,
    val iconEmoji: String
)

@Composable
fun SectionHeader(
    title: String,
    icon: ImageVector,
    isDark: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp), // Respect screen edge padding
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(12.dp))
                .clayCard(isDark = isDark, shape = RoundedCornerShape(28.dp), elevation = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun OfflineGamesHorizontalSection(
    games: List<GameItem>,
    isDark: Boolean,
    onRoomClick: (GameItem) -> Unit,
    onStartClick: (GameItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeader(
            title = "لیست بازی‌های آفلاین",
            icon = Icons.Default.Devices,
            isDark = isDark
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Edge-to-edge LazyRow with 20.dp content padding so items align with screen edges cleanly
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp)
        ) {
            items(games) { game ->
                OfflineGameCard(
                    game = game,
                    isDark = isDark,
                    onRoomClick = { onRoomClick(game) },
                    onStartClick = { onStartClick(game) }
                )
            }
        }
    }
}

@Composable
fun OfflineGameCard(
    game: GameItem,
    isDark: Boolean,
    onRoomClick: () -> Unit,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(28.dp)
    Box(
        modifier = modifier
            .width(300.dp)
            .shadow(elevation = 5.dp, shape = shape)
            .clip(shape)
            .clayCard(isDark = isDark, shape = shape, elevation = 28.dp)
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Top Preview Image / Illustration Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .clayInset(isDark = isDark, shape = RoundedCornerShape(28.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = game.iconEmoji, fontSize = 48.sp)
            }

            // Title & Description
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = game.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = game.category,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Text(
                    text = game.description,
                    fontSize = 12.5.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                    maxLines = 2,
                    lineHeight = 18.sp
                )
            }

            // Action Buttons Row with Exact Equal Width (weight = 1f each)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ClaySecondaryButton(
                        text = "اتاق‌ها",
                        onClick = onRoomClick,
                        isDark = isDark,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    ClayPrimaryButton(
                        text = "شروع بازی",
                        onClick = onStartClick,
                        isDark = isDark,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
