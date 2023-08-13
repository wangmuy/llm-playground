package ui

import kmputil.KMPViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class NavigationViewModel(): KMPViewModel() {
    companion object {
        const val SCREEN_CHAT = 0
        const val SCREEN_SETTING = 1
    }

    val navigationState: MutableStateFlow<NavigationUiState> = MutableStateFlow(NavigationUiState())

    fun onSelectScreen(screenId: Int) {
        navigationState.update {
            it.copy(currentScreenId = screenId)
        }
    }
}

data class NavigationUiState(
    val currentScreenId: Int = NavigationViewModel.SCREEN_CHAT
)