package com.example.fctc3.screens.bottom_screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fctc3.models.Empresa
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.viewmodels.screens.EmpresaViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmpresasScreen(navController: NavHostController, viewModel: EmpresaViewModel)
{
    val state = rememberPullToRefreshState()
    var searchText by remember { mutableStateOf("") }
    val empresas by viewModel.empresas.observeAsState(initial = viewModel.empresas.value ?: emptyList())


    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch something
            delay(1500)
            viewModel.añadirEmpresas()
            state.endRefresh()
        }
    }
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

        /*val empresa1 = Empresa(
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
        )*/

        //val empresas: List<Empresa> = listOf(empresa1, empresa2, empresa1, empresa2)


        Column (
            modifier = Modifier.fillMaxSize().nestedScroll(state.nestedScrollConnection)
        )
        {
            val filteredItems = remember(searchText) {
                empresas.filter { it.name.contains(searchText, ignoreCase = true)
                        || it.personaContacto.contains(searchText, ignoreCase = true) }}


            LazyColumn {
                if (!state.isRefreshing) {
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

            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                state = state
            )

        }
    }
}



@Composable
fun EmpresaItem(empresa: Empresa, navController: NavHostController, viewModel: EmpresaViewModel)
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
                        viewModel.empresa.value = empresa
                        navController.navigate(route = AppScreens.SolicitudesScreen.route)
                    },
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.List, contentDescription = "List Icon")
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
                        viewModel.removeEmpresaByNif(empresa.nif)
                        viewModel.eliminarEmpresa(empresa.nif)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }
        }
    }
}