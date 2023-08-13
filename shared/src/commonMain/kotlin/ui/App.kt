package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import data.service.LangChainService
import data.source.local.ChatInMemoryDataSource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.theme.AppTheme

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val viewModel = remember {
        ChatScreenViewModel(ChatInMemoryDataSource(), LangChainService())
    }

    AppTheme {
        ChatScreen(viewModel)
    }
}
