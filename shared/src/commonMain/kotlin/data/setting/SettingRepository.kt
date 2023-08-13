package data.setting

import data.model.ChatConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class SettingRepository: SettingDataSource {
    companion object {
        private const val TAG = "SettingRepository"
    }
    private val _configMSF = MutableStateFlow(ChatConfig())
    private val config = _configMSF.asStateFlow()
    private val observableConfig: Flow<ChatConfig> = config.map { it }

    override suspend fun getConfig(): ChatConfig {
        return observableConfig.first()
    }

    override fun getConfigStream(): Flow<ChatConfig> {
        return observableConfig
    }

    override suspend fun saveConfig(config: ChatConfig) {
        _configMSF.update {
            val newConfig = it.copy()
            if (config.apiKey.isNotEmpty()) {
                newConfig.apiKey = config.apiKey
            }
            if (config.baseUrl.isNotEmpty()) {
                newConfig.baseUrl = config.baseUrl
            }
            if (config.timeoutMillis >= 0) {
                newConfig.timeoutMillis = config.timeoutMillis
            }
            if (config.proxy.isNotEmpty()) {
                newConfig.proxy = config.proxy
            }
            if (config.llmConfig.isNotEmpty()) {
                newConfig.llmConfig = config.llmConfig
            }
            newConfig
        }
    }
}