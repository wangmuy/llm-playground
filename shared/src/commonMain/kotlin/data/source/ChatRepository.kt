package data.source

import data.model.ChatMessage
import data.model.ChatSession
import data.source.local.ChatInMemoryDataSource
import kotlinx.coroutines.flow.Flow

class ChatRepository(
    private val inMemoryDataSource: ChatInMemoryDataSource
): ChatDataSource {

    override suspend fun getMessages(sessionId: Int): List<ChatMessage> {
        return inMemoryDataSource.getMessages(sessionId)
    }

    override fun getMessageStream(sessionId: Int): Flow<List<ChatMessage>> {
        return inMemoryDataSource.getMessageStream(sessionId)
    }

    override suspend fun addMessage(sessionId: Int, message: ChatMessage) {
        return inMemoryDataSource.addMessage(sessionId, message)
    }
}