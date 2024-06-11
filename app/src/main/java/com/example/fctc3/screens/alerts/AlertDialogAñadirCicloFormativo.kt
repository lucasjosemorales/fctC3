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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fc3.viewmodels.AlertDialogViewModel
import com.example.fctc3.models.Ciclo
import com.example.fctc3.viewmodels.bbdd.CicloViewModel
import com.example.fctc3.viewmodels.screens.ProfesorViewModel


@Composable
fun AlertDialogAñadirCicloFormativo(showDialog: MutableState<Boolean>)
{
    /*var nombreCorto by remember { mutableStateOf("") }
    var nombreLargo by remember { mutableStateOf("") }
    var familia by remember { mutableStateOf("") }*/

    val cicloViewModel: CicloViewModel = viewModel()
    val viewModel: AlertDialogViewModel = viewModel()

    val firstInput: String by viewModel.firstInput.observeAsState(initial = "")
    val secondInput: String by viewModel.secondInput.observeAsState(initial = "")
    val thirdInput: String by viewModel.thirdInput.observeAsState(initial = "")

    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    if (showDialog.value)
    {
        AlertDialog(
            onDismissRequest = {
                // Handle outside clicks to dismiss
                showDialog.value = false
            },
            title = {
                Text(text = "Añadir Ciclo Formativo",
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

                    Spacer(modifier = Modifier.padding(4.dp))

                    OutlinedTextField(
                        value = secondInput,
                        onValueChange = { viewModel.setSecondInput(it) },
                        label = { Text("Nombre largo") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    OutlinedTextField(
                        value = thirdInput,
                        onValueChange = { viewModel.setThirdInput(it) },
                        label = { Text("Familia") },
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
                    viewModel.habilitarAñadirCicloFormativo(
                        firstInput, secondInput, thirdInput)

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = {

                            // Confirm action
                            cicloViewModel.añadirCiclo(
                                Ciclo(
                                    nombreCorto = firstInput,
                                    nombreLargo = secondInput,
                                    familia = thirdInput
                                )
                            )
                            showDialog.value = false
                        },
                       enabled = loginEnable
                    ) {
                        Text("Confirmar")
                    }
                }
            }
        )
    }
}
