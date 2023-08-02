package kmputil

import android.util.Log

actual abstract class KMPLog {
    actual companion object {
        actual fun d(tag: String, msg: String) {
            Log.d(tag, msg)
        }

        actual fun e(tag: String, msg: String, t: Throwable) {
            Log.e(tag, msg, t)
        }
    }
}