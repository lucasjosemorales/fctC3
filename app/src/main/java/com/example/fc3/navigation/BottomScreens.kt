package com.example.fc3.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fctc3.R


@Preview
@Composable
fun EmpresasScreen()
{
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Cyan)
    )
    {
        Text(
            text = "Empresas",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 50.sp
        )

    }
}

@Preview
@Composable
fun ProfesoresScreen()
{
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    )
    {
        Text(
            text = "Profesores",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 50.sp
        )

    }
}

@Preview
@Composable
fun AlumnosScreen()
{
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    )
    {
        Text(
            text = "Alumnos",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 50.sp
        )

    }
}

@Preview
@Composable
fun SolicitudesScreen()
{
    Box(
        modifier = Modifier.fillMaxSize().background(Color.DarkGray)
    )
    {
        Text(
            text = "Solicitudes",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 50.sp
        )

    }
}

