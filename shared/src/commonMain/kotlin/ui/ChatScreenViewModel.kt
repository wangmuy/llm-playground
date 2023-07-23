package ui

import data.model.ChatMessage
import data.source.ChatDataSource
import kmputil.KMPViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timestampMs

class ChatScreenViewModel(
    private val chatDataSource: ChatDataSource
): KMPViewModel() {

    val screenUiState: MutableStateFlow<ChatScreenUiState> = MutableStateFlow(ChatScreenUiState())

    val currentChatUiState: StateFlow<ChatMessagesUiState> = chatDataSource
        .getMessageStream(1).map { ChatMessagesUiState.Success(it) }
        .stateIn(
        scope = coroutineScope,
        started = SharingStarted.Eagerly,
        initialValue = ChatMessagesUiState.Loading
    )

    fun onTextChange(inputText: String) {
        screenUiState.update { it.copy(inputText = inputText) }
    }

    fun onSendMessage() {
        coroutineScope.launch {
            val currentInput = screenUiState.value.inputText
            screenUiState.update { it.copy(inputText = "") } // TODO isSending=true

            chatDataSource.addMessage(1,
                ChatMessage(role = "me", content = currentInput, timeMs = timestampMs()))
            // TODO send message
            // TODO stop loading
        }
    }
}

sealed interface ChatMessagesUiState {
    object Empty: ChatMessagesUiState
    object Loading: ChatMessagesUiState
    data class Success(
        val messages: List<ChatMessage> = emptyList()
    ): ChatMessagesUiState
}

data class ChatScreenUiState(
    val inputText: String = "",
    val isSending: Boolean = false
)