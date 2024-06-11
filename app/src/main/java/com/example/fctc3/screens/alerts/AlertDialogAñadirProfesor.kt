package com.example.fctc3.screens.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fctc3.screens.formularios.FormularioProfesorScreen
import com.example.fctc3.viewmodels.screens.ProfesorViewModel


@Composable
fun AlertDialogAñadirProfesor(navController: NavHostController, showDialog: MutableState<Boolean>)
{
    val viewModel:ProfesorViewModel = viewModel()


    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Handle outside clicks to dismiss
                showDialog.value = false
            },
            title = {
                Text(text = "Añadir Nuevo Profesor",
                    modifier = Modifier
                        .fillMaxWidth()  // Asegura que el modificador toma todo el ancho
                        .wrapContentWidth(Alignment.CenterHorizontally)  // Centra el contenido horizontalmente
                )
            },
            text = {
                LazyColumn {
                    item {
                        FormularioProfesorScreen(navController, viewModel.profesor.value!!)
                    }
                }
            },
            confirmButton = {
                /*Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = {

                            // Confirm action
                            showDialog.value = false
                        }
                    ) {
                        Text("Confirmar")
                    }
                }*/
            }
        )
    }
}
