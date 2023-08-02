package kmputil

actual abstract class KMPLog {
    actual companion object {
        actual fun d(tag: String, msg: String) {
            println("$tag: $msg")
        }

        actual fun e(tag: String, msg: String, t: Throwable) {
            println("$tag: $msg\n${t.stackTraceToString()}")
        }
    }
}