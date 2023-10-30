package com.grx.lsc.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    value: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    label: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    errorMessage: String? = null,
    isError: Boolean = !errorMessage.isNullOrEmpty(),
    borderColor: Color = if (isError) Color.Red else Color.LightGray,
) {
    Column {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth().border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            ),
            singleLine = singleLine,
            value = value,
            isError = isError,
            leadingIcon = leadingIcon,
            onValueChange = onValueChange,
            label = { Text(text = label) }
        )

        if (!errorMessage.isNullOrBlank()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = errorMessage, color = Color.Red)
        }
    }

}
