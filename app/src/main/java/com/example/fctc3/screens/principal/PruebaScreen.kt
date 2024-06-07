package com.example.fctc3.screens.principal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PruebaScreen() {

    val (text, setText) = remember { mutableStateOf("") }
    val (errorText, setErrorText) = remember { mutableStateOf<String?>(null) }

    Column {

        OutlinedTextField(
            value = text,
            onValueChange = { input ->
                setText(input)
                setErrorText(if (input.isEmpty()) "Cannot be empty" else null)
            },
            label = { Text("Username") },
            isError = errorText != null,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { /* Handle the 'Done' action */ })
        )
        errorText?.let {
            Text(it, color = Color.Red)
        }

    }
}



@Preview
@Composable
fun PreviewErrorOutlinedTextField() {
    PruebaScreen()
}