package com.example.fc3.screens.bottom_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.fc3.viewmodels.AlumnoViewModel
import com.example.fc3.viewmodels.ProfesorViewModel

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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Lista de botones
            listOf("Añadir profesor", "Eliminar profesor", "Añadir Ciclo Formativo", "Eliminar Ciclo Formativo", "Añadir grupo", "Eliminar grupo").forEach { buttonText ->
                Button(
                    elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp, pressedElevation = 12.dp),
                    onClick = { /* Acción del botón */ },
                    shape = CutCornerShape(
                        topStart = 0f,
                        topEnd = 0f,
                        bottomEnd = 0f,
                        bottomStart = 100f
                    ), // Bordes redondeados
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF364F59)),
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 10.dp)
                ) {
                    Text(buttonText)
                }
            }
        }
    }
}