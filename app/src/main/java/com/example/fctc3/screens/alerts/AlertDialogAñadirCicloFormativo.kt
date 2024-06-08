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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fctc3.models.Ciclo
import com.example.fctc3.viewmodels.bbdd.CicloViewModel


@Composable
fun AlertDialogAñadirCicloFormativo(showDialog: MutableState<Boolean>)
{
    var nombreCorto by remember { mutableStateOf("") }
    var nombreLargo by remember { mutableStateOf("") }
    var familia by remember { mutableStateOf("") }

    val cicloViewModel: CicloViewModel = viewModel()

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
                        value = nombreCorto,
                        onValueChange = { nombreCorto = it },
                        label = { Text("Nombre corto") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    OutlinedTextField(
                        value = nombreLargo,
                        onValueChange = { nombreLargo = it },
                        label = { Text("Nombre largo") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    OutlinedTextField(
                        value = familia,
                        onValueChange = { familia = it },
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
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = {
                            // Confirm action
                            cicloViewModel.añadirCiclo(
                                Ciclo(
                                    nombreCorto = nombreCorto,
                                    nombreLargo = nombreLargo,
                                    familia = familia
                                )
                            )
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
