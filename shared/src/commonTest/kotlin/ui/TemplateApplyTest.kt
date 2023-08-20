package ui

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TemplateApplyTest {
    @Test fun testApplyTemplateSimple() {
        val templateStr = "hello {input} end"
        val input = "[input]\nworld"
        val applied = ChatScreenViewModel.applyTemplate(templateStr, input)
        assertEquals("hello world end", applied)
    }

    @Test fun testApplyTemplateMultiline() {
        val templateStr = "chat histories:\n{chat_history}"
        val chatHistories = "role=ai, content=hello\nrole=human, content=hello"
        val input = "[chat_history]\n$chatHistories"
        val applied = ChatScreenViewModel.applyTemplate(templateStr, input)
        assertEquals("chat histories:\n$chatHistories", applied)
    }
}