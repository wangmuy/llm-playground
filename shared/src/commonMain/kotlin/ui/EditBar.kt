package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditBar(
    inputText: String,
    onTextChange: (String) -> Unit,
    onSend: () -> Unit
) {
    val enableSend = inputText.isNotBlank()

    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.Bottom) {
        Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.weight(1f)) {
            TextField(
                modifier = Modifier.fillMaxWidth(1f).padding(10.dp),
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                value = inputText,
                placeholder = { Text("Type message...") },
                onValueChange = onTextChange
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onSend,
            enabled = enableSend,
            modifier = Modifier.size(48.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)) {
            Icon(Icons.Rounded.Send, contentDescription = "Send")
        }
    }
}