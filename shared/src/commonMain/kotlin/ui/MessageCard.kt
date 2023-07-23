package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.ChatMessage

@Composable
fun MessageCard(msg: ChatMessage) {
    Row {
        Column {
            Text(text = msg.role, color = MaterialTheme.colors.secondary, style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = msg.content, style = MaterialTheme.typography.body2)
        }
    }
}