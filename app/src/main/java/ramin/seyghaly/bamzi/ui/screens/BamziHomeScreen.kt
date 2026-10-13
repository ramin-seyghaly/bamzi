package ramin.seyghaly.bamzi.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import ramin.seyghaly.bamzi.ui.components.BamziToolbar
import ramin.seyghaly.bamzi.ui.theme.BamziTheme

@Composable
fun BamziHomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
    initialDarkTheme: Boolean = false
) {
    var isDark by remember { mutableStateOf(initialDarkTheme) }

    BamziTheme(darkTheme = isDark) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding() // Proper padding from system status bar
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Floating Claymorphic Toolbar matching web app header
                BamziToolbar(
                    isDark = isDark,
                    onThemeToggle = { isDark = !isDark },
                    onLoginClick = onLoginClick,
                    onRegisterClick = onRegisterClick
                )

                Spacer(modifier = Modifier.height(30.dp))

                // Additional home content preview can go here
            }
        }
    }
}

@Preview(name = "Bamzi Home - Light", showBackground = true, widthDp = 412, heightDp = 891)
@Composable
fun BamziHomeScreenLightPreview() {
    BamziHomeScreen(onLoginClick = {}, onRegisterClick = {}, initialDarkTheme = false)
}

@Preview(name = "Bamzi Home - Dark", showBackground = true, widthDp = 412, heightDp = 891)
@Composable
fun BamziHomeScreenDarkPreview() {
    BamziHomeScreen(onLoginClick = {}, onRegisterClick = {}, initialDarkTheme = true)
}
