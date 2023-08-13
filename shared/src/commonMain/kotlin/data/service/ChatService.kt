package data.service

import data.model.ChatMessage

interface ChatService {
    fun setService(apiKey: String, baseUrl: String, timeoutMillis: Long, proxy: String?)
    fun setLLMConfig(configs: Map<String, String>)
    suspend fun sendMessage(message: ChatMessage): Result<ChatMessage>
}