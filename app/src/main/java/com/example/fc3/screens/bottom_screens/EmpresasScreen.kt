package com.example.fc3.screens.bottom_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.fc3.viewmodels.EmpresaViewModel

@Composable
fun EmpresasScreen(navController: NavHostController, viewModel: EmpresaViewModel)
{
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    )
    {

        SearchBar(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 0.dp),
            onSearch = { newText ->
                searchText = newText
            }
        )

        val empresa1 = Empresa(
            nif = "A12345678",
            name = "Soluciones Innovadoras S.L.",
            localidad = "Madrid",
            personaContacto = "Ana Martínez",
            tfnoContacto = "912345678",
            email = "contacto@solucionesinnovadoras.com",
            repetidor = true,
            contratar = true,
            dual= true,
            observaciones = "Tenemos sede en Murcia"
        )

        val empresa2 = Empresa(
            nif = "B87654321",
            name = "Tecnologías Avanzadas S.A.",
            localidad = "Barcelona",
            personaContacto = "Carlos Gómez",
            tfnoContacto = "932765432",
            email = "info@tecnologiasavanzadas.es"
        )

        val empresa3 = Empresa(
            nif = "B87654321",
            name = "Tecnologías Avanzadas S.A.",
            localidad = "Barcelona",
            personaContacto = "Carlos Gómez",
            tfnoContacto = "932765432",
            email = "info@tecnologiasavanzadas.es"
        )

        val empresa4 = Empresa(
            nif = "A12345678",
            name = "Soluciones Innovadoras S.L.",
            localidad = "Madrid",
            personaContacto = "Ana Martínez",
            tfnoContacto = "912345678",
            email = "contacto@solucionesinnovadoras.com"
        )

        //val empresas: List<Empresa> = listOf(empresa1, empresa2, empresa3, empresa4)

        val empresas: List<Empresa> = obtenerEmpresas()

        val filteredItems = remember(searchText) {
            empresas.filter { it.name.contains(searchText, ignoreCase = true)
                    || it.personaContacto.contains(searchText, ignoreCase = true) }}


        LazyColumn {
            filteredItems.forEach{ empresa ->
                item {
                    EmpresaItem(empresa = empresa, navController, viewModel)
                }

            }
            item{
                Spacer(modifier =  Modifier.height(72.dp))
            }
        }

    }
}



@Composable
fun EmpresaItem(empresa: Empresa, navController: NavHostController, viewModel: EmpresaViewModel)
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
                        viewModel.empresa.value= empresa
                        navController.navigate(route = AppScreens.FormularioEmpresaScreen.route)
            })
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(6f)) {
                Text(
                    text = empresa.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
               /* Text(
                    text = empresa.tfnoContacto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = empresa.email,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))*/
                Text(
                    text = empresa.personaContacto,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
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