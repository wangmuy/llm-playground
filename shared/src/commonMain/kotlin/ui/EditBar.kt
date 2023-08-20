package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Build
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditBar(
    inputText: String,
    templateText: String,
    showTemplate: Boolean,
    onTextChange: (String) -> Unit,
    onTemplateChange: (String) -> Unit,
    onShowTemplateChange: () -> Unit,
    onSend: () -> Unit
) {
    val enableSend = inputText.isNotBlank()

    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.Bottom) {
        Column(modifier = Modifier.weight(1f).fillMaxWidth()) {
            if (showTemplate) {
                TextField(
                    modifier = Modifier.fillMaxWidth(1f)
                        .padding(end = 10.dp)
                        .heightIn(max = 200.dp),
                    value = templateText,
                    placeholder = { Text("Put template here...") },
                    onValueChange = onTemplateChange
                )
            }
            TextField(
                modifier = Modifier.fillMaxWidth(1f)
                    .padding(top = 10.dp, end = 10.dp)
                    .heightIn(max = 200.dp),
                value = inputText,
                placeholder = { Text("Simple text or simple ini style key-value for the template...") },
                onValueChange = onTextChange
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.size(width = 48.dp, height = 96.dp)) {
            Row(
                modifier = Modifier.size(48.dp)
                    .clickable(
                        onClick = onShowTemplateChange
                    )
            ) {
                Checkbox(
                    modifier = Modifier.size(16.dp),
                    checked = showTemplate,
                    onCheckedChange = null
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(Icons.Rounded.Build, contentDescription = "Use Template")
            }
            Button(
                modifier = Modifier.size(48.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                onClick = onSend,
                enabled = enableSend) {
                Icon(Icons.Rounded.Send, contentDescription = "Send")
            }
        }
    }
}