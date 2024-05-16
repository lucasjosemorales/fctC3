package com.example.fc3.screens.bottom_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fc3.models.Empresa
import com.example.fc3.navigation.AppScreens

@Composable
fun EmpresasScreen(navController: NavHostController)
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
                    EmpresaItem(empresa = empresa, navController)
                }

            }
            item{
                Spacer(modifier =  Modifier.height(72.dp))
            }
        }

    }
}

@Composable
fun EmpresaItem(empresa: Empresa, navController: NavHostController)
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
            .fillMaxWidth()
            .clickable { navController.navigate(route = AppScreens.FormularioEmpresaScreen.route) })
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(6f)) {
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

            //Spacer(modifier = Modifier.width(32.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {

                IconButton(
                    onClick = {

                    },
                ) {
                    Icon(imageVector = Icons.Default.List, contentDescription = "List Icon")
                }

                IconButton(
                    onClick = {
                        navController.navigate(route = AppScreens.FormularioSolicitudScreen.route)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
                }

                IconButton(
                    onClick = {
                        //Dialog preguntando si estás seguro + Borrado de la BBDD + Actualizar vista de la Lista
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }
        }
    }
}