package com.example.fc3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fctc3.R
import com.example.fc3.models.Empresa
import com.example.fc3.navigation.AppScreens


@Preview
@Composable
fun EmpresasScreen()
{
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Cyan)
    )
    {
        /*Text(
            text = "Empresas",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 50.sp
        )*/

        val empresa1 = Empresa(
            nif = "A12345678",
            name = "Soluciones Innovadoras S.L.",
            localidad = "Madrid",
            personaContacto = "Ana Martínez",
            tfnoContacto = "912345678",
            email = "contacto@solucionesinnovadoras.com"
        )

        val empresa2 = Empresa(
            nif = "B87654321",
            name = "Tecnologías Avanzadas S.A.",
            localidad = "Barcelona",
            personaContacto = "Carlos Gómez",
            tfnoContacto = "932765432",
            email = "info@tecnologiasavanzadas.es"
        )

        val empresas: List<Empresa> = listOf(empresa1, empresa2)

        LazyColumn {
            empresas.forEach{ empresa ->
                item {
                    EmpresaItem(empresa = empresa)
                }
            }
        }

    }
}

@Composable
fun EmpresaItem(empresa: Empresa)
{
    //var showDeleteEmpresaDialog by remember { mutableStateOf(false)}

    /*val onDeleteEmpresaConfirmed: () -> Unit = {
        realtime.deleteEmpresa(empresa.key ?: "")
    }*/

    /*
    if (showDeleteEmpresaDialog) {
        DeleteEmpresaDialog(
            onConfirmDelete = {
                onDeleteEmpresaConfirmed()
                showDeleteEmpresaDialog = false
            },
            onDismiss = {
                showDeleteEmpresaDialog = false
            }
        )
    }*/

    Card(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 0.dp)
            .fillMaxWidth())
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(3f)) {
                Text(
                    text = empresa.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = empresa.tfnoContacto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = empresa.email,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = empresa.personaContacto,
                    fontWeight = FontWeight.Thin,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {

                IconButton(
                    onClick = {
                        //showDeleteEmpresaDialog = true
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }

                IconButton(
                    onClick = {
                        //navController.navigate(route = AppScreens.FormularioEmpresaScreen.route)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
                }
            }
        }
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

