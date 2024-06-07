package com.example.fctc3.screens.bottom_screens

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.viewmodels.FirestoreViewModel
import com.example.fctc3.viewmodels.ProfesorViewModel
import com.example.fct.models.Profesor
import com.example.fctc3.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch


@Composable
fun ProfesoresScreen(navController: NavHostController, viewModel: ProfesorViewModel)
{
    var searchText by remember { mutableStateOf("") }

    //val firestoreViewModel = FirestoreViewModel()
    //val profesores = firestoreViewModel.profesores.collectAsState().value
    //val isLoading = firestoreViewModel.isLoading.collectAsState().value


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

            //Room
            val profesores: List<Profesor> by viewModel.profesores.observeAsState(initial = emptyList())
            //viewModel.cargarProfesores()

            //val profesores: List<Profesor> = viewModel.state.listaProfesores
            //firestoreViewModel.fetchProfesores()

            val filteredItems = remember(searchText) {
                profesores.filter { it.name.contains(searchText, ignoreCase = true)
                        || it.tutoria.contains(searchText, ignoreCase = true) }}

            LazyColumn {
                filteredItems?.forEach{ profesor ->
                    item {
                        ProfesorItem(profesor = profesor, navController, viewModel)
                    }
                }

                item{
                    Spacer(modifier =  Modifier.height(72.dp))
                }
            }

        }
    }





@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfesorItem(profesor: Profesor, navController: NavHostController, viewModel: ProfesorViewModel)
{

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
                viewModel.profesor.value = profesor
                navController.navigate(route = AppScreens.FormularioProfesorScreen.route) }
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
                    text = profesor.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                /*Text(
                    text = profesor.email,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = profesor.phoneNumber,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))*/
                Text(
                    text = profesor.tutoria,
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

                        intent.data = Uri.parse("tel:${profesor.email}")
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

                        intent.data = Uri.parse("tel:${profesor.phoneNumber}")
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
                            intent.data = Uri.parse("tel:${profesor. phoneNumber}")

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

            }

           /* Column(
                modifier = Modifier.weight(1f)
            ) {

            }*/
        }
    }
}