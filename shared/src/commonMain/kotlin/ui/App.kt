package ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import data.service.ChatService
import data.service.LangChainService
import data.setting.SettingDataSource
import data.setting.SettingRepository
import data.source.ChatDataSource
import data.source.local.ChatInMemoryDataSource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import ui.theme.AppTheme

val chatDataSource: ChatDataSource = ChatInMemoryDataSource()
val langChainSource: ChatService = LangChainService()
val settingDataSource: SettingDataSource = SettingRepository()

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    val navigationViewModel = remember { NavigationViewModel() }
    val navigationState by navigationViewModel.navigationState.collectAsState()

    val chatViewModel = remember {
        ChatScreenViewModel(chatDataSource, langChainSource)
    }

    val settingViewModel = remember {
        SettingScreenViewModel(langChainSource, settingDataSource)
    }

    AppTheme {
        Scaffold(
            topBar = {
                PlaygroundTopBar(
                    onClickChat = {
                        navigationViewModel.onSelectScreen(NavigationViewModel.SCREEN_CHAT)
                    },
                    onClickSetting = {
                        navigationViewModel.onSelectScreen(NavigationViewModel.SCREEN_SETTING)
                    }
                )
            }
        ) {contentPadding->
            when (navigationState.currentScreenId) {
                NavigationViewModel.SCREEN_CHAT -> {
                    ChatScreen(contentPadding, chatViewModel)
                }
                NavigationViewModel.SCREEN_SETTING -> {
                    SettingScreen(
                        contentPadding,
                        settingViewModel
                    )
                }
                else -> Unit
            }
        }
    }
}
