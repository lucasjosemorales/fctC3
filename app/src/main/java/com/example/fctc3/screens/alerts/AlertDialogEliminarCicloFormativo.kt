package com.example.fctc3.screens.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fc3.viewmodels.AlertDialogViewModel
import com.example.fctc3.viewmodels.bbdd.CicloViewModel


@Composable
fun AlertDialogEliminarCicloFormativo(showDialog: MutableState<Boolean>)
{

    val cicloViewModel: CicloViewModel = viewModel()
    val viewModel: AlertDialogViewModel = viewModel()
    val firstInput: String by viewModel.firstInput.observeAsState(initial = "")

    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    val context = LocalContext.current

    if (showDialog.value)
    {
        AlertDialog(
            onDismissRequest = {
                // Handle outside clicks to dismiss
                showDialog.value = false
            },
            title = {
                Text(text = "Eliminar Ciclo Formativo",
                    modifier = Modifier
                        .fillMaxWidth()  // Asegura que el modificador toma todo el ancho
                        .wrapContentWidth(Alignment.CenterHorizontally)  // Centra el contenido horizontalmente
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = firstInput,
                        onValueChange = { viewModel.setFirstInput(it) },
                        label = { Text("Nombre corto") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    //Comprobamos todos los campos y habilitamos el boton
                    viewModel.habilitarEliminarCiclo(firstInput)

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = {
                            // Confirm action
                            cicloViewModel.eliminarCiclo(firstInput)
                            showDialog.value = false},
                        enabled = loginEnable

                    ) {
                        Text("Confirmar")
                    }
                }
            }
        )
    }
}
