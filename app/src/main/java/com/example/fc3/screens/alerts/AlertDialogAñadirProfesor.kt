package com.example.fc3.screens.alerts

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fc3.navigation.AppScreens
import com.example.fc3.screens.formularios.FormularioProfesorScreen
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun AlertDialogAñadirProfesor(navController: NavHostController, showDialog: MutableState<Boolean>)
{
    //var showDialog by remember { mutableStateOf(true) }
    var firstInput by remember { mutableStateOf("") }
    var secondInput by remember { mutableStateOf("") }

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
                FormularioProfesorScreen(navController, null)
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
