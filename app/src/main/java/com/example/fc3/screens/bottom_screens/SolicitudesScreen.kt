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
import com.example.fc3.models.Solicitud
import com.example.fct.models.Alumno
import com.example.fct.models.Profesor
import com.example.fctc3.R
import java.util.LinkedHashMap


@Composable
fun SolicitudesScreen(navController: NavHostController)
{

    Column (
        modifier = Modifier.fillMaxSize().background(Color.Red)
    )
    {

        val solicitud1 = Solicitud(
            nif= "A12345678",
            name = "Soluciones Innovadoras S.L.",
            funciones= "Programación App Web",
            horario= "L-V 8-14",
            alumnos= LinkedHashMap<String, Int>()
        )

        val solicitud2 = Solicitud(
            nif= "B87654321",
            name = "Tecnologías Avanzadas S.A.",
            funciones= "Programación App Móvil",
            horario= "L-V 8-15",
            alumnos=LinkedHashMap<String, Int>()
        )

        solicitud1.alumnos.put("DAW", 1)
        solicitud2.alumnos.put("DAM", 2)

        val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)

        LazyColumn {
            solicitudes.forEach{ solicitud ->
                item {
                    SolicitudItem( solicitud= solicitud, navController)
                }
            }

            /* item{
                 Spacer(modifier =  Modifier.height(72.dp))
             }*/
        }



    }
}


@Composable
fun SolicitudItem(solicitud: Solicitud, navController: NavHostController)
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
            Column(
                modifier = Modifier.weight(6f)
            )
            {
                Text(
                    text = solicitud.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = solicitud.nif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)

                Text(
                    text = solicitud.horario,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = solicitud.funciones,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                solicitud.alumnos.forEach { (ciclo, numero) ->
                    Text(
                        text = "$ciclo:$numero",
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                }

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
