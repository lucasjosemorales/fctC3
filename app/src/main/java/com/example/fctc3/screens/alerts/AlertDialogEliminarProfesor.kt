package com.example.fctc3.screens.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fctc3.viewmodels.screens.ProfesorViewModel


@Composable
fun AlertDialogEliminarProfesor(showDialog: MutableState<Boolean>, viewModel1: ProfesorViewModel)
{
    val viewModel2:ProfesorViewModel = viewModel()
    var firstInput by remember { mutableStateOf("") }

    if (showDialog.value)
    {
        AlertDialog(
            onDismissRequest = {
                // Handle outside clicks to dismiss
                showDialog.value = false
            },
            title = {
                Text(text = "Eliminar Profesor",
                    modifier = Modifier
                        .fillMaxWidth()  // Asegura que el modificador toma todo el ancho
                        .wrapContentWidth(Alignment.CenterHorizontally)  // Centra el contenido horizontalmente
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = firstInput,
                        onValueChange = { firstInput = it },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = {
                            //viewModel.eliminarTodos()
                            viewModel2.eliminarProfesor(firstInput)
                            viewModel2.removeProfesorByEmail(firstInput)
                            // Confirm action
                            showDialog.value = false
                        }
                    ) {
                        Text("Confirmar")
                    }
                }
            }
        )
    }
}
