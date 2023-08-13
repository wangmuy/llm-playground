package ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable

@Composable
fun PlaygroundTopBar(
    onClickChat: () -> Unit,
    onClickSetting: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "LLMPlayground") },
        actions = {
            IconButton(onClick = onClickChat) {
                Icon(Icons.Rounded.Edit, contentDescription = "chat")
            }
            IconButton(onClick = onClickSetting) {
                Icon(Icons.Rounded.Info, contentDescription = "settings")
            }
        }
    )
}