package data.service

import com.wangmuy.llmchain.serviceprovider.openai.OpenAIChat
import data.model.ChatConfig
import data.model.ChatMessage
import data.suspendRunCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LangChainService(
    val config: ChatConfig = ChatConfig(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
): ChatService {

    private var llm: OpenAIChat? = null
    private var currentConfig: ChatConfig? = null

    override fun setService(apiKey: String, baseUrl: String, timeoutMillis: Long, proxy: String?) {
        config.apiKey = apiKey
        config.baseUrl = baseUrl
        config.timeoutMillis = timeoutMillis
        config.proxy = proxy ?: ""
    }

    override fun setLLMConfig(configs: Map<String, Any>) {
        config.llmConfig.clear()
        // todo
        config.llmConfig.putAll(configs)
    }

    private fun getChatService(): OpenAIChat {
        if (currentConfig != config) {
            val cfg = config.copy().also {
                currentConfig = it
            }
            val proxy = cfg.proxy.ifEmpty { null }
            llm = OpenAIChat(
                apiKey = cfg.apiKey,
                baseUrl = cfg.baseUrl,
                timeoutMillis = cfg.timeoutMillis,
                proxy = proxy,
                invocationParams = cfg.llmConfig
            )
        }
        return llm!!
    }

    override suspend fun sendMessage(
        message: ChatMessage
    ): Result<ChatMessage> = suspendRunCatching(dispatcher) {
        val reply = getChatService().invoke(message.content, emptyList())
        ChatMessage(
            role = ChatMessage.ROLE_BOT,
            content = reply)
    }
}