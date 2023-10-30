package com.grx.lsc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OtpView(
    otpValue: String,
    onValueChange: (String) -> Unit,
    errorMessage: String? = null,
    isError: Boolean = !errorMessage.isNullOrEmpty(),
    borderColor: Color = if (isError) Color.Red else Color.LightGray,
) {
    Column {
        BasicTextField(
            modifier = Modifier
                .border(1.dp,  borderColor, RoundedCornerShape(8.dp)),
            value = otpValue,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            textStyle = MaterialTheme.typography.bodyMedium,
            visualTransformation = VisualTransformation.None,
            decorationBox = { innerTextField ->
                Row(horizontalArrangement = Arrangement.Center) {
                    repeat(6) { index ->
                        val char = otpValue.getOrNull(index)?.toString() ?: ""
                        Box(
                            modifier = Modifier
                                .width(48.dp)
                                .height(48.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = char,
                                style = MaterialTheme.typography.bodyLarge,
                                color = borderColor,
                                textAlign = TextAlign.Center
                            )
                        }
                        if (index != 5) {
                            Box(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(48.dp)
                                    .background(color = borderColor)
                                    .padding(2.dp),
                            )
                        }
                    }
                }
            }
        )
        if (!errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = errorMessage, color = Color.Red)
        }
    }
}
