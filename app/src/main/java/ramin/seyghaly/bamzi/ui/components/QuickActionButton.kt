package ramin.seyghaly.bamzi.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ramin.seyghaly.bamzi.ui.theme.OrangePrimary

@Composable
fun QuickActionButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    contentColor: Color = OrangePrimary
) {
    Surface(
        modifier = modifier.size(56.dp),
        shape = CircleShape,
        color = containerColor,
        shadowElevation = 2.dp,
        border = BorderStroke(1.dp, Color.Black.copy(alpha = 0.05f))
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
