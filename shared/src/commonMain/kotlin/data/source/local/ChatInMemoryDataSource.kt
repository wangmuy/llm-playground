package data.source.local

import atomicInt
import data.model.ChatMessage
import data.source.ChatDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class ChatInMemoryDataSource: ChatDataSource {
    companion object {
        private const val TAG = "ChatInMemoryDataSource"
    }

    private val _messagesMSF = MutableStateFlow(ArrayList<ChatMessage>())
    private val messages = _messagesMSF.asStateFlow()

    private val observableMessages: Flow<List<ChatMessage>> = messages.map { it }

    override suspend fun getMessages(sessionId: Int): List<ChatMessage> {
        return observableMessages.first()
    }

    override fun getMessageStream(sessionId: Int): Flow<List<ChatMessage>> {
        return observableMessages
    }

    override suspend fun addMessage(sessionId: Int, message: ChatMessage) {
        _messagesMSF.update {oldMessages->
            val newMessages = ArrayList(oldMessages)
            newMessages.add(ChatMessage(id = atomicInt(TAG),
                role = message.role, content = message.content, timeMs = message.timeMs))
            newMessages
        }
    }
}