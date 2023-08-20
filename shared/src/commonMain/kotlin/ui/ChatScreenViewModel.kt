package ui

import com.wangmuy.llmchain.prompt.PromptTemplate
import data.model.ChatMessage
import data.service.ChatService
import data.source.ChatDataSource
import kmputil.KMPViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ui.util.Async

class ChatScreenViewModel(
    private val chatDataSource: ChatDataSource,
    private val chatService: ChatService
): KMPViewModel() {
    companion object {
        fun applyTemplate(templateText: String, inputText: String): String {
            var tempKey: String? = null
            var tempValue: String? = null
            val args = HashMap<String, String>()
            inputText.splitToSequence("\n").forEach {line->
                if (line.startsWith("[") && line.endsWith("]")) {
                    if (tempKey != null) {
                        args[tempKey!!] = tempValue ?: ""
                    }
                    tempKey = line.substring(1, line.length-1)
                    tempValue = null
                } else {
                    tempValue = (if (tempValue == null) "" else "$tempValue\n") + line
                }
            }
            if (tempKey != null) {
                args[tempKey!!] = tempValue ?: ""
            }
            if (args.size == 0) {
                args["input"] = inputText
            }
            val promptTemplate = PromptTemplate(args.keys.toList(), templateText)
            return promptTemplate.format(args)
        }
    }

    val screenUiState: MutableStateFlow<ChatScreenUiState> = MutableStateFlow(ChatScreenUiState())

    val currentChatUiState: StateFlow<Async<List<ChatMessage>>> = chatDataSource
        .getMessageStream(1).map { Async.Success(it) }
        .catch<Async<List<ChatMessage>>> { emit(Async.Error("error loading chat histories")) }
        .stateIn(
        scope = coroutineScope,
        started = SharingStarted.Eagerly,
        initialValue = Async.Loading
    )

    fun onTextChange(inputText: String) {
        screenUiState.update {
            it.copy(inputText = inputText)
        }
    }

    fun onTemplateChange(templateText: String) {
        screenUiState.update {
            it.copy(templateText = templateText)
        }
    }

    fun onShowTemplateChange() {
        screenUiState.update {
            val oldValue = it.showTemplate
            it.copy(showTemplate = !oldValue)
        }
    }

    fun onSendMessage() {
        coroutineScope.launch {
            val template = screenUiState.value.templateText.trim()
            val input = screenUiState.value.inputText
            screenUiState.update { it.copy(inputText = "", isSending = true) }

            val showTemplate = screenUiState.value.showTemplate
            val sendText = if (template.isNotEmpty() && showTemplate)
                applyTemplate(template, input)
            else
                input

            val msg = ChatMessage(
                role = ChatMessage.ROLE_ME,
                content = sendText)
            chatDataSource.addMessage(1, msg)

            val sendResult = chatService.sendMessage(msg)
            screenUiState.update {
                it.copy(isSending = false)
            }
            val replyMsg = sendResult.getOrElse {e->
                ChatMessage(
                    role = ChatMessage.ROLE_APP,
                    content = e.stackTraceToString())
            }
            chatDataSource.addMessage(1, replyMsg)
        }
    }
}

data class ChatScreenUiState(
    val inputText: String = "",
    val templateText: String = "",
    val showTemplate: Boolean = true,
    val isSending: Boolean = false
)