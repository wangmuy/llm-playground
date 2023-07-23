package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(viewModel: ChatScreenViewModel) {
    val conversationUiState by viewModel.currentChatUiState.collectAsState()
    val screenUiState by viewModel.screenUiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(Modifier.weight(1f)) {
            when (conversationUiState) {
                ChatMessagesUiState.Empty,
                ChatMessagesUiState.Loading -> Unit

                is ChatMessagesUiState.Success -> {
                    Conversation((conversationUiState as ChatMessagesUiState.Success).messages)
                }
            }
        }
        EditBar(
            inputText = screenUiState.inputText,
            onTextChange = viewModel::onTextChange,
            onSend = viewModel::onSendMessage)
    }
}