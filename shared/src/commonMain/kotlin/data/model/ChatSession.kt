package data.model

class ChatSession(
    val id: Int,
    val title: String,
    val messages: MutableList<ChatMessage> = mutableListOf(),
    val timeMs: Long = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ChatSession

        if (id != other.id) return false
        if (title != other.title) return false
        if (messages != other.messages) return false
        if (timeMs != other.timeMs) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + messages.hashCode()
        result = 31 * result + timeMs.hashCode()
        return result
    }

    override fun toString(): String {
        return "ChatSession(id=$id, title='$title', messages=$messages, timeMs=$timeMs)"
    }
}