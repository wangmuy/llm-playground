package kmputil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

actual abstract class KMPViewModel: ViewModel() {
    actual val coroutineScope = viewModelScope

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    protected actual override fun onCleared() {
        super.onCleared()
    }
}