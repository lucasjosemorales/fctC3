package com.example.fc3.screens.bottom_screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fc3.models.Solicitud
import com.example.fc3.navigation.AppScreens
import com.example.fctc3.R
import kotlinx.coroutines.launch
import java.util.LinkedHashMap


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SolicitudesScreen(navController: NavHostController)
{

    Column (
        modifier = Modifier.fillMaxSize()
    )
    {

        val tabs = listOf("Nuevas", "Asignadas", "Completadas")
        val pagerState = rememberPagerState(pageCount = {
            3
        })

        Column(modifier = Modifier.fillMaxSize())
        {
            val coroutineScope = rememberCoroutineScope()
            // TabRow for displaying the tabs
            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            // Animate to the selected page
                            coroutineScope.launch{
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            // Horizontal pager that switches between tab contents
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                pageContent = { page ->
                    when (page) {
                        0 -> TabContent1(navController)
                        1 -> TabContent2(navController)
                        2 -> TabContent3(navController)
                    }
                }
            )
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

    var checked by remember { mutableStateOf(false) }
    var coordinador by remember { mutableStateOf(false) }

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
            .clickable { navController.navigate(route = AppScreens.FormularioSolicitudScreen.route) }
    )
    {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(6f)
            )
            {
                Text(
                    text = solicitud.empresa,
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

                solicitud.plazas.forEach { (ciclo, numero) ->
                    Text(
                        text = "$ciclo:$numero",
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                }

            Spacer(modifier = Modifier.width(32.dp))

            Column(
                modifier = Modifier.weight(1f).fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End,
            ) {

                Switch(
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color(0xFF364F59),
                        checkedTrackColor = Color(0xFF647C87),
                        uncheckedThumbColor = Color(0xFF364F59),
                        uncheckedTrackColor = Color.White,
                        checkedBorderColor = Color(0xFF364F59),
                        uncheckedBorderColor = Color(0xFF364F59)
                    ),
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        if (checked)
                            coordinador = true
                        else
                            coordinador = false
                    },
                    modifier = Modifier.graphicsLayer(scaleX = 0.8f, scaleY = 0.8f)
                )

                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_add), // Icono de Email
                        contentDescription = "Mail Icon")
                }

                IconButton(
                    onClick = {
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_remove), // Icono de Email
                        contentDescription = "Mail Icon")
                }

                IconButton(
                    onClick = {
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenWithTabs(navController: NavHostController)
{
    val tabs = listOf("Nuevas", "Asignadas", "Completadas")
    val pagerState = rememberPagerState(pageCount = {
        3
    })

        Column(modifier = Modifier.fillMaxSize())
        {
            val coroutineScope = rememberCoroutineScope()
            // TabRow for displaying the tabs
            TabRow(selectedTabIndex = pagerState.currentPage) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = pagerState.currentPage == index,
                        onClick = {
                            // Animate to the selected page
                            coroutineScope.launch{
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

            // Horizontal pager that switches between tab contents
            HorizontalPager(
                modifier = Modifier.weight(1f),
                state = pagerState,
                pageContent = { page ->
                    when (page) {
                        0 -> TabContent1(navController)
                        1 -> TabContent2(navController)
                        2 -> TabContent3(navController)
                    }
                }
            )
        }
}

@Composable
fun TabContent1(navController: NavHostController)
{
    val solicitud1 = Solicitud(
        nif= "A12345678",
        empresa = "Soluciones Innovadoras S.L.",
        funciones= "Programación App Web",
        horario= "L-V 8-14",
        plazas= LinkedHashMap<String, Int>(),
        estado = "Nueva",
        coordinador = "juan.perez@escuela.edu",
        alumnos = null
    )

    val solicitud2 = Solicitud(
        nif= "B87654321",
        empresa = "Tecnologías Avanzadas S.A.",
        funciones= "Programación App Móvil",
        horario= "L-V 8-15",
        plazas=LinkedHashMap<String, Int>(),
        estado="Nueva",
        coordinador = "juan.perez@escuela.edu",
        alumnos = null
    )

    solicitud1.plazas.put("DAW", 1)
    solicitud2.plazas.put("DAM", 2)

    val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)
    LazyColumn {
        solicitudes.forEach{ solicitud ->
            item {
                SolicitudItem( solicitud= solicitud, navController)
            }
        }

    }
}

@Composable
fun TabContent2(navController: NavHostController)
{
    val solicitud1 = Solicitud(
        nif= "A12345678",
        empresa = "Soluciones Innovadoras S.L.",
        funciones= "Programación App Web",
        horario= "L-V 8-14",
        plazas= LinkedHashMap<String, Int>(),
        estado = "Asignada",
        coordinador = "juan.perez@escuela.edu",
        alumnos = null
    )

    val solicitud2 = Solicitud(
        nif= "B87654321",
        empresa = "Tecnologías Avanzadas S.A.",
        funciones= "Programación App Móvil",
        horario= "L-V 8-15",
        plazas=LinkedHashMap<String, Int>(),
        estado="Asignada",
        coordinador = "juan.perez@escuela.edu",
        alumnos = null
    )

    solicitud1.plazas.put("DAW", 1)
    solicitud2.plazas.put("DAM", 2)

    val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)
    LazyColumn {
        solicitudes.forEach{ solicitud ->
            item {
                SolicitudItem( solicitud= solicitud, navController)
            }
        }
    }
}

@Composable
fun TabContent3(navController: NavHostController)
{
    val solicitud1 = Solicitud(
        nif= "A12345678",
        empresa = "Soluciones Innovadoras S.L.",
        funciones= "Programación App Web",
        horario= "L-V 8-14",
        plazas= LinkedHashMap<String, Int>(),
        estado = "Completada",
        coordinador = "juan.perez@escuela.edu",
        alumnos = null
    )

    val solicitud2 = Solicitud(
        nif= "B87654321",
        empresa = "Tecnologías Avanzadas S.A.",
        funciones= "Programación App Móvil",
        horario= "L-V 8-15",
        plazas=LinkedHashMap<String, Int>(),
        estado="Completada",
        coordinador = "juan.perez@escuela.edu",
        alumnos = null
    )

    solicitud1.plazas.put("DAW", 1)
    solicitud2.plazas.put("DAM", 2)

    val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)
    LazyColumn {
        solicitudes.forEach{ solicitud ->
            item {
                SolicitudItem( solicitud= solicitud, navController)
            }
        }
    }
}
