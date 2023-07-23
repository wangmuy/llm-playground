import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

actual fun getPlatformName(): String = "Android"

actual fun timestampMs(): Long = System.currentTimeMillis()

private val atomicIntStore = ConcurrentHashMap<String, AtomicInteger>()
actual fun atomicInt(tag: String): Int = atomicIntStore.getOrPut(tag) {
    AtomicInteger(0)
}.incrementAndGet()