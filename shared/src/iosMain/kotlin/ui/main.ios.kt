package ui
import androidx.compose.ui.window.ComposeUIViewController
import ui.App

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = ComposeUIViewController { App() }