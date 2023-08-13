package data.model

data class ChatConfig(
    var apiKey: String = "",
    var baseUrl: String = "https://api.openai.com/v1/",
    var timeoutMillis: Long = 10000,
    var proxy: String = "", // http://127.0.0.1:1091 http://192.168.3.129:1091
    var llmConfig: MutableMap<String, Any> = HashMap()
)