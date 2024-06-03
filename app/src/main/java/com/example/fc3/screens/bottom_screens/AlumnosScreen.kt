package com.example.fc3.screens.bottom_screens

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fc3.navigation.AppScreens
import com.example.fc3.viewmodels.AlumnoViewModel
import com.example.fct.models.Alumno
import com.example.fctc3.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun SearchBar(modifier: Modifier, onSearch: (String) -> Unit)
{
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        value = searchText,
        onValueChange = {
            searchText = it
            onSearch(it)
        },
        modifier = modifier,
        label = { Text("Texto a buscar...") },
        singleLine = true,
    )
}

@Composable
fun AlumnosScreen(navController: NavHostController, viewModel: AlumnoViewModel)
{

    var searchText by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize()
    )
    {

        SearchBar(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 0.dp),
            onSearch = { newText ->
                searchText = newText
            }
        )

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
        val filteredItems = remember(searchText) {
            alumnos.filter { it.name.contains(searchText, ignoreCase = true) || it.grupo.contains(searchText, ignoreCase = true) }}

        LazyColumn {
            filteredItems.forEach{ alumno ->
                item {
                    AlumnoItem(alumno = alumno, navController, viewModel)
                }
            }
        }




    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AlumnoItem(alumno: Alumno, navController: NavHostController, viewModel: AlumnoViewModel) {
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
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color(0xFF364F59),
            disabledContentColor = Color(0xFF364F59) ,
            disabledContainerColor = Color.White
        ),
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 0.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.alumno.value = alumno
                navController.navigate(route = AppScreens.FormularioAlumnoScreen.route)
            }
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(5f)) {
                Text(
                    text = alumno.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                //Spacer(modifier = Modifier.height(10.dp))
                /*Text(
                    text = alumno.email,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )*/
                Spacer(modifier = Modifier.height(10.dp))
               /* Text(
                    text = alumno.phoneNumber,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )*/
                //Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = alumno.grupo,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {

                val context = LocalContext.current
                val intent = Intent(Intent.ACTION_SEND)

                IconButton(
                    onClick = {

                        intent.data = Uri.parse("tel:${alumno.email}")
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, "Hola, esto es un mensaje de prueba.")

                        if (intent.resolveActivity(context.packageManager) != null)
                            context.startActivity(intent)
                        else
                            Toast.makeText(context, "Error al abrir la app", Toast.LENGTH_LONG).show()

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mail), // Icono de Email
                        contentDescription = "Mail Icon")
                }
                IconButton(
                    onClick = {

                        intent.data = Uri.parse("tel:${alumno.phoneNumber}")
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, "Hola, esto es un mensaje de prueba.")
                        //intent.setPackage("com.whatsapp")

                        if (intent.resolveActivity(context.packageManager) != null)
                            context.startActivity(intent)
                        else
                            Toast.makeText(context, "Error al abrir la app", Toast.LENGTH_LONG).show()

                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.whatsapp), // Icono de chat para representar WhatsApp
                        contentDescription = "WhatsApp Icon")
                }

                //val context = LocalContext.current
                val callPermissionState = rememberPermissionState(permission = Manifest.permission.CALL_PHONE)

                IconButton(
                    onClick = {
                        if (callPermissionState.status.isGranted)
                        {
                            val intent = Intent(Intent.ACTION_CALL)
                            intent.data = Uri.parse("tel:${alumno. phoneNumber}")

                            if (intent.resolveActivity(context.packageManager) != null)
                                context.startActivity(intent)
                        }
                        else
                        {
                            callPermissionState.launchPermissionRequest()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.phone), // Icono de Teléfono
                        contentDescription = "Phone Icon")
                }

                IconButton(
                    onClick = {
                        //Dialog preguntando si estás seguro + Borrado de la BBDD + Actualizar vista de la Lista
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }

            /*Column(
                modifier = Modifier.weight(1f)
            ) {
                IconButton(
                    onClick = {
                        //Dialog preguntando si estás seguro + Borrado de la BBDD + Actualizar vista de la Lista
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }*/
        }
    }
}
