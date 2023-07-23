package kmputil

import kotlinx.coroutines.CoroutineScope

// https://hlnstepanova.github.io/kmpizza/step-12-add-shared-viewmodel-layer/
expect abstract class KMPViewModel() {
    val coroutineScope: CoroutineScope

    fun dispose()

    protected open fun onCleared()
}