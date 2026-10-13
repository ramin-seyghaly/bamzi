package ramin.seyghaly.bamzi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun HomeBanner(
    banners: List<String>,
    modifier: Modifier = Modifier
) {
    if (banners.isEmpty()) return

    val pagerState = rememberPagerState(pageCount = { banners.size })
    val context = LocalContext.current

    // Auto-slide effect
    LaunchedEffect(banners.size) {
        while (true) {
            delay(3000L)
            if (!pagerState.isScrollInProgress) {
                val nextPage = (pagerState.currentPage + 1) % banners.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                beyondViewportPageCount = 1
            ) { page ->
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(banners[page])
                        .crossfade(true)
                        .build(),
                    contentDescription = "Banner ${page + 1}",
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentScale = ContentScale.Crop
                )
            }

            // Page Indicators
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(banners.size) { index ->
                    val color = if (pagerState.currentPage == index) {
                        Color.White
                    } else {
                        Color.White.copy(alpha = 0.5f)
                    }
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(color)
                    )
                }
            }
        }
    }
}
