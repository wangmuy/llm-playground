package kmputil

actual fun getPlatformName(): String = "iOS"

actual fun timestampMs(): Long = 1L

actual fun atomicInt(tag: String): Int = 1