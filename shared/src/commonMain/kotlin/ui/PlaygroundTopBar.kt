package ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun PlaygroundTopBar(
    currentScreenId: Int,
    onClickChat: () -> Unit,
    onClickSetting: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "LLMPlayground") },
        actions = {
            IconButton(onClick = onClickChat) {
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "chat",
                    tint = getScreenButtonTint(NavigationViewModel.SCREEN_CHAT, currentScreenId)
                )
            }
            IconButton(onClick = onClickSetting) {
                Icon(
                    Icons.Rounded.Info,
                    contentDescription = "settings",
                    tint = getScreenButtonTint(NavigationViewModel.SCREEN_SETTING, currentScreenId)
                )
            }
        }
    )
}

@Composable
fun getScreenButtonTint(buttonId: Int, currentScreenId: Int) =
    if (currentScreenId == buttonId)
        Color.Red
    else
        LocalContentColor.current