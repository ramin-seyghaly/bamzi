package ramin.seyghaly.bamzi.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ramin.seyghaly.bamzi.ui.components.BamziToolbar
import ramin.seyghaly.bamzi.ui.components.OfflineGamesHorizontalSection
import ramin.seyghaly.bamzi.ui.theme.BamziTheme

@Composable
fun HomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    initialDarkTheme: Boolean = false,
    viewModel: HomeViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    var isDark by remember { mutableStateOf(initialDarkTheme) }
    val offlineGamesList by viewModel.games.collectAsStateWithLifecycle()

    BamziTheme(darkTheme = isDark) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Toolbar with horizontal padding matching edge
                Box {
                    BamziToolbar(
                        isDark = isDark,
                        onThemeToggle = { isDark = !isDark },
                        onLoginClick = onLoginClick,
                        onRegisterClick = onRegisterClick
                    )
                }

                // Horizontal Offline Games Section with edge-to-edge LazyRow
                OfflineGamesHorizontalSection(
                    games = offlineGamesList,
                    isDark = isDark,
                    onRoomClick = {},
                    onStartClick = {}
                )
            }
        }
    }
}

@Preview(name = "Bamzi Home - Light", showBackground = true, widthDp = 412, heightDp = 891)
@Composable
fun HomeScreenLightPreview() {
    HomeScreen(onLoginClick = {}, onRegisterClick = {}, initialDarkTheme = false)
}

@Preview(name = "Bamzi Home - Dark", showBackground = true, widthDp = 412, heightDp = 891)
@Composable
fun HomeScreenDarkPreview() {
    HomeScreen(onLoginClick = {}, onRegisterClick = {}, initialDarkTheme = true)
}
