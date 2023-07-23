package data.source

import data.model.ChatMessage
import data.model.ChatSession
import kotlinx.coroutines.flow.Flow

interface ChatDataSource {
    suspend fun getMessages(sessionId: Int): List<ChatMessage>
    fun getMessageStream(sessionId: Int): Flow<List<ChatMessage>>

    suspend fun addMessage(sessionId: Int, message: ChatMessage)
}