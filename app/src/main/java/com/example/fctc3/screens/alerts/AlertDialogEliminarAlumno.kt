package com.example.fctc3.screens.alerts

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fc3.viewmodels.AlertDialogViewModel
import com.example.fctc3.viewmodels.screens.AlumnoViewModel
import com.example.fctc3.viewmodels.screens.ProfesorViewModel

@Composable
fun AlertDialogEliminarAlumno()
{
    val viewModelAlumno: AlumnoViewModel = viewModel()
    val viewModel: AlertDialogViewModel = viewModel()
    val firstInput: String by viewModel.firstInput.observeAsState(initial = "")

    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    val showDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current

    if (showDialog.value)
    {
        if(isLoading){
            Box(Modifier.fillMaxSize()){
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
        }else{
            viewModel.reestablecerValores()

            AlertDialog(
                onDismissRequest = {
                    // Handle outside clicks to dismiss
                    showDialog.value = false
                },
                title = {
                    Text(text = "Eliminar Alumno",
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
                        //Comprobamos todos los campos y habilitamos el boton
                        //viewModel.habilitarEliminarProfesor(firstInput)

                        Button(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            onClick = {
                                showDialog.value = false
                                //viewModelAlumno.eliminarAlumno(firstInput)
                                //viewModelAlumno.removeAlumnoByEmail(firstInput)
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
}
