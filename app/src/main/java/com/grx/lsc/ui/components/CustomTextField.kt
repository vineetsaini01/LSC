package com.grx.lsc.ui.components

import androidx.compose.foundation.layout.*
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
    trailingIcon: @Composable (() -> Unit)? = null,
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
            modifier = modifier.fillMaxWidth(),
            singleLine = singleLine,
            value = value,
            isError = isError,
            enabled=enabled,
            trailingIcon = trailingIcon,
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
