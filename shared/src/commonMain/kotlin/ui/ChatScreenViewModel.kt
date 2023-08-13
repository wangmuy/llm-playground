package ui

import data.model.ChatMessage
import data.service.ChatService
import data.source.ChatDataSource
import kmputil.KMPViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatScreenViewModel(
    private val chatDataSource: ChatDataSource,
    private val chatService: ChatService
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
        screenUiState.update {
            it.copy(inputText = inputText, isSending = true)
        }
    }

    fun onSendMessage() {
        coroutineScope.launch {
            val currentInput = screenUiState.value.inputText
            screenUiState.update { it.copy(inputText = "") }

            val msg = ChatMessage(
                role = ChatMessage.ROLE_ME,
                content = currentInput,
                timeMs = kmputil.timestampMs())
            chatDataSource.addMessage(1, msg)

            val sendResult = chatService.sendMessage(msg)
            screenUiState.update {
                it.copy(isSending = false)
            }
            val replyMsg = sendResult.getOrElse {e->
                ChatMessage(
                    role = ChatMessage.ROLE_APP,
                    content = e.stackTraceToString(),
                    timeMs = kmputil.timestampMs())
            }
            chatDataSource.addMessage(1, replyMsg)
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