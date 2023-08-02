package kmputil

expect abstract class KMPLog {
    companion object {
        fun d(tag: String, msg: String)

        fun e(tag: String, msg: String, t: Throwable)
    }
}