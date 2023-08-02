package kmputil

actual abstract class KMPLog {
    actual companion object {
        actual fun d(tag: String, msg: String) {
            ;
        }

        actual fun e(tag: String, msg: String, t: Throwable) {
            ;
        }
    }
}