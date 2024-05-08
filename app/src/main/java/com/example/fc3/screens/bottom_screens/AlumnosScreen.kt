package com.example.fc3.screens.bottom_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fct.models.Alumno
import com.example.fct.models.Profesor
import com.example.fctc3.R


@Composable
fun AlumnosScreen(navController: NavHostController)
{

    Column (
        modifier = Modifier.fillMaxSize().background(Color.Red)
    )
    {

        val alumno1 = Alumno(
            name = "Juan Pérez",
            email = "juan.perez@alu.murciaeduca.es",
            phoneNumber = "123456789",
            grupo = "2DAM-D"
        )

        val alumno2 = Alumno(
            name = "Ana Gómez",
            email = "ana.gomez@alu.murciaeduca.es",
            phoneNumber = "987654321",
            grupo = "2DAW-D"
        )

        val alumno3 = Alumno(
            name = "Carlos Martínez",
            email = "carlos.martinez@alu.murciaeduca.es",
            phoneNumber = "555666777",
            grupo = "2DAW"
        )

        val alumnos: List<Alumno> = listOf(alumno1, alumno2, alumno3, alumno1, alumno2, alumno3)

        LazyColumn {
            alumnos.forEach{ alumno ->
                item {
                    AlumnoItem(alumno = alumno, navController)
                }
            }

           /* item{
                Spacer(modifier =  Modifier.height(72.dp))
            }*/
        }



    }
}


@Composable
fun AlumnoItem(alumno: Alumno, navController: NavHostController)
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
                    text = alumno.name,
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
                        text = alumno.email,
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
                        text = alumno.phoneNumber,
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
                    text = alumno.grupo,
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
