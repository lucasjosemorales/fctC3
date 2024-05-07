package com.example.fc3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.fc3.models.Empresa
import com.example.fc3.navigation.AppScreens
import com.example.fct.models.Profesor
import com.example.fctc3.R


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
            .fillMaxWidth())
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

@Composable
fun ProfesoresScreen(navController: NavHostController)
{

    Column (
        modifier = Modifier.fillMaxSize().background(Color.Red)
    )
    {

        val profesor1 = Profesor(
            name = "Juan Pérez",
            email = "juan.perez@escuela.edu",
            phoneNumber = "123456789",
            tutoria = "2DAM-D"
        )

        val profesor2 = Profesor(
            name = "Ana Gómez",
            email = "ana.gomez@escuela.edu",
            phoneNumber = "987654321",
            tutoria = "2DAW-D"
        )

        val profesor3 = Profesor(
            name = "Carlos Martínez",
            email = "carlos.martinez@escuela.edu",
            phoneNumber = "555666777",
            tutoria = "2DAW"
        )

        val profesores: List<Profesor> = listOf(profesor1, profesor2, profesor3, profesor1, profesor2, profesor3)

        LazyColumn {
            profesores.forEach{ profesor ->
                item {
                    ProfesorItem(profesor = profesor, navController)
                }
            }

            item{
                Spacer(modifier =  Modifier.height(72.dp))
            }
        }



    }
}


@Composable
fun ProfesorItem(profesor: Profesor, navController: NavHostController)
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
            verticalAlignment = Alignment.Top,
        ) {
            Column(modifier = Modifier.weight(6f)) {
                Text(
                    text = profesor.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text(
                        text = profesor.email,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)

                    Icon(
                        painter = painterResource(id = R.drawable.mail), // Icono de chat para representar WhatsApp
                        contentDescription = "Mail Icon",
                        modifier = Modifier.padding(start = 8.dp) // Espacio entre el texto y el icono
                    )

                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth() // Añade un padding para estética
                ) {
                    Text(
                        text = profesor.phoneNumber,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.chat), // Icono de chat para representar WhatsApp
                        contentDescription = "WhatsApp Icon",
                        modifier = Modifier.padding(start = 8.dp) // Espacio entre el texto y el icono
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.phone), // Icono de chat para representar WhatsApp
                        contentDescription = "Phone Icon",
                        modifier = Modifier.padding(start = 8.dp) // Espacio entre el texto y el icono
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = profesor.tutoria,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            }

            //Spacer(modifier = Modifier.width(32.dp))

            Column(
                modifier = Modifier.weight(1f).fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End,
            ) {
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

