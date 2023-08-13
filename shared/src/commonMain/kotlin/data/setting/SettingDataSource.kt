package data.setting

import data.model.ChatConfig
import kotlinx.coroutines.flow.Flow

interface SettingDataSource {
    suspend fun getConfig(): ChatConfig
    fun getConfigStream(): Flow<ChatConfig>
    suspend fun saveConfig(config: ChatConfig)
}