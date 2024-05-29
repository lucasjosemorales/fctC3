package com.example.fc3.screens.bottom_screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fc3.screens.alerts.*


@Composable
fun AdminScreen(navController: NavHostController)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF647C87), Color.White)
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        val showDialog = remember { mutableStateOf(false) }
        var dialogItem = remember { mutableStateOf(0) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {


            if (showDialog.value)
            {
                when(dialogItem.value)
                {
                    0 -> AlertDialogAñadirProfesor(navController, showDialog)
                    1 -> AlertDialogEliminarProfesor(showDialog)
                    2 -> AlertDialogAñadirCicloFormativo(showDialog)
                    3 -> AlertDialogEliminarCicloFormativo(showDialog)
                    4 -> AlertDialogAñadirGrupo(showDialog)
                    5 -> AlertDialogEliminarGrupo(showDialog)
                    else -> AlertDialogAñadirProfesor(navController, showDialog)
                }
            }

            // Lista de botones
            listOf("Añadir profesor", "Eliminar profesor", "Añadir Ciclo Formativo", "Eliminar Ciclo Formativo", "Añadir grupo", "Eliminar grupo").
            forEach { buttonText ->

                Surface(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 32.dp), // Espacio para el borde
                    shape = CutCornerShape(
                        topStart = 0f,
                        topEnd = 0f,
                        bottomEnd = 0f,
                        bottomStart = 100f
                    ), // Borde redondeado
                    border = BorderStroke(2.dp, Color(0xFF364F59)), // Establece el color y grosor del borde

                ) {
                    ElevatedButton(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF364F59)),
                        onClick = {
                                        showDialog.value = true
                                        dialogItem.value = when (buttonText)
                                        {
                                            "Añadir profesor" -> 0
                                            "Eliminar profesor" -> 1
                                            "Añadir Ciclo Formativo" -> 2
                                            "Eliminar Ciclo Formativo" -> 3
                                            "Añadir grupo" -> 4
                                            "Eliminar grupo" -> 5
                                            else -> -1
                                        }
                        },
                        elevation = ButtonDefaults.elevatedButtonElevation(
                            defaultElevation = 8.dp,
                            pressedElevation = 12.dp
                        ),
                        /*modifier = ,*/ // Relleno para separar el contenido del borde
                        shape = CutCornerShape(
                            topStart = 0f,
                            topEnd = 0f,
                            bottomEnd = 0f,
                            bottomStart = 100f
                        ) // Debe ser ligeramente menor que el Surface para coincidir
                    ) {
                        Text(buttonText)
                    }
                }
            }
        }
    }
}