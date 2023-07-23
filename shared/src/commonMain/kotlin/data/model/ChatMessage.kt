package data.model

open class ChatMessage(
    val id: Int = 0,
    val role: String,
    val content: String,
    val timeMs: Long
) {
    companion object {
        const val ROLE_ME = "me"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ChatMessage

        if (role != other.role) return false
        if (content != other.content) return false
        if (timeMs != other.timeMs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = role.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + timeMs.hashCode()
        return result
    }

    override fun toString(): String {
        return "ChatMessage(role='$role', content='$content', timestamp=$timeMs)"
    }
}