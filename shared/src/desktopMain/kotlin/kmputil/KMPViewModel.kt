package kmputil

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class KMPViewModel {
    actual val coroutineScope = MainScope()

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    protected actual open fun onCleared() {}
}