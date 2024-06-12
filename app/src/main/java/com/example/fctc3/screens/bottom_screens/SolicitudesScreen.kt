package com.example.fctc3.screens.bottom_screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fc3.viewmodels.AlertDialogViewModel
import com.example.fctc3.models.Solicitud
import com.example.fctc3.navigation.AppScreens
import com.example.fctc3.viewmodels.screens.EmpresaViewModel
import com.example.fctc3.viewmodels.screens.SolicitudViewModel
import com.example.fctc3.R
import com.example.fctc3.bbdd.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.delay



@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SolicitudesScreen(navController: NavHostController, viewModelEmpresa: EmpresaViewModel, viewModel: SolicitudViewModel)
//fun SolicitudesScreen(navController: NavHostController, empresa: Empresa?)
{

    //val viewModel: SolicitudViewModel = viewModel()
    //var nifComparar by remember { mutableStateOf(viewModel.empresa.value?.nif ?: "") }


    Column (
        modifier = Modifier.fillMaxSize()
    )
    {

        val state = rememberPullToRefreshState()
        var searchText by remember { mutableStateOf("") }
        val solicitudes by viewModel.solicitudes.observeAsState(initial = viewModel.solicitudes.value ?: emptyList())
        val nifCompare by viewModel.nif.observeAsState(initial = viewModel.nif.value ?: "")

        if (state.isRefreshing) {
            LaunchedEffect(true) {
                // fetch something
                delay(1500)
                viewModel.añadirSolicitudes()
                state.endRefresh()
            }
        }
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

            /*val solicitud1 = Solicitud(
                nif= "A12345678",
                empresa = "Soluciones Innovadoras S.L.",
                funciones= "Programación App Web",
                horario= "L-V 8-14",
                plazas= LinkedHashMap<String, Int>(),
                estado = "Nueva",
                coordinador = "juan.perez@escuela.edu",
                alumnos = null
            )

            solicitud1.plazas["Sistemas Microinformáticos y Redes G.M."] = 2

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

            solicitud2.plazas["Desarrollo de Aplicaciones Multiplataforma G.S."] = 1*/

            //val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)

            Column (
                modifier = Modifier.fillMaxSize().nestedScroll(state.nestedScrollConnection)
            )
            {
                if (nifCompare.isNullOrEmpty()){
                    val filteredItems = remember(searchText) {
                        solicitudes.filter {it.empresa.contains(searchText, ignoreCase = true)
                                || it.funciones.contains(searchText, ignoreCase = true)}}

                    LazyColumn {
                        if (!state.isRefreshing) {
                            filteredItems.forEach{ solicitud ->
                                item {
                                    SolicitudItem( solicitud= solicitud, navController, viewModel)
                                }
                            }
                        }
                    }
                } else{
                    val filteredNifItems = remember(nifCompare) {
                        solicitudes.filter { it.nif.equals(nifCompare, ignoreCase = true)}}

                    LazyColumn {
                        if (!state.isRefreshing) {
                            filteredNifItems.forEach{ solicitud ->
                                item {
                                    SolicitudItem( solicitud= solicitud, navController, viewModel)
                                }
                            }
                        }
                    }
                }

                PullToRefreshContainer(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    state = state
                )
            }

        }
        /*
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
                        0 -> TabContent1(navController, nifComparar, viewModelSolicitudes)
                        1 -> TabContent2(navController, nifComparar, viewModelSolicitudes)
                        2 -> TabContent3(navController, nifComparar, viewModelSolicitudes)
                    }
                }
            )
        }*/
    }
}


@Composable
fun SolicitudItem(solicitud: Solicitud, navController: NavHostController, viewModel: SolicitudViewModel) {
    val checked by viewModel.checked.observeAsState(initial = solicitud.checked)
    val coordinador by viewModel.coordinador.observeAsState(initial = solicitud.coordinador)
    val hayCoordinador = (coordinador.isNullOrEmpty()) || (coordinador == com.google.firebase.ktx.Firebase.auth.currentUser?.email)

    val viewModelDialog: AlertDialogViewModel = viewModel()
    val firstInput: String by viewModelDialog.firstInput.observeAsState(initial = "")
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    var showAddAlumnoDialog by remember { mutableStateOf(false) }
    var showDeleteAlumnoDialog by remember { mutableStateOf(false) }
    //val showDialog = remember { mutableStateOf(false) }

    //-------------------AÑADIR ALUMNO A LA LISTA
    if (showAddAlumnoDialog) {
        viewModelDialog.reestablecerValores()

        AlertDialog(
            onDismissRequest = {
                // Handle outside clicks to dismiss
                showAddAlumnoDialog = false
            },
            title = {
                Text(text = "Añadir Alumno",
                    modifier = Modifier
                        .fillMaxWidth()  // Asegura que el modificador toma todo el ancho
                        .wrapContentWidth(Alignment.CenterHorizontally)  // Centra el contenido horizontalmente
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = firstInput,
                        onValueChange = { viewModelDialog.setFirstInput(it) },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    //Comprobamos todos los campos y habilitamos el boton
                    //viewModelDialog.habilitarAñadirAlumno(firstInput)

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = {
                            viewModel.añadirAlumnoLista(firstInput)
                            solicitud.alumnos.add(firstInput)
                            showAddAlumnoDialog = false

                            com.google.firebase.ktx.Firebase.firestore
                                .collection("solicitudes")
                                .document(solicitud.nif)
                                .update("alumnos", solicitud.alumnos)
                                .addOnSuccessListener {
                                    //Toast.makeText(context, "Coordinador actualizado", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener { e ->
                                    //Log.w("Firestore", "Error al actualizar el documento", e)
                                }
                            //viewModelAlumno.eliminarAlumno(firstInput)
                            //viewModelAlumno.removeAlumnoByEmail(firstInput)
                        }
                    ) {
                        Text("Confirmar")
                    }
                }
            }
        )

    }

//---------------ELIMINAR ALUMNO A LA LISTA
    if (showDeleteAlumnoDialog) {
        viewModelDialog.reestablecerValores()

        AlertDialog(
            onDismissRequest = {
                // Handle outside clicks to dismiss
                showDeleteAlumnoDialog = false
            },
            title = {
                Text(text = "Eliminar Alumno",
                    modifier = Modifier
                        .fillMaxWidth()  // Asegura que el modificador toma todo el ancho
                        .wrapContentWidth(Alignment.CenterHorizontally)  // Centra el contenido horizontalmente
                )
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = firstInput,
                        onValueChange = { viewModelDialog.setFirstInput(it) },
                        label = { Text("Correo electrónico") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    //Comprobamos todos los campos y habilitamos el boton
                    //viewModelDialog.habilitarAñadirAlumno(firstInput)

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        onClick = {
                            viewModel.eliminarAlumnoLista(firstInput)
                            solicitud.alumnos.remove(firstInput)
                            showDeleteAlumnoDialog = false

                            com.google.firebase.ktx.Firebase.firestore
                                .collection("solicitudes")
                                .document(solicitud.nif)
                                .update("alumnos", solicitud.alumnos)
                                .addOnSuccessListener {
                                    //Toast.makeText(context, "Coordinador actualizado", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener { e ->
                                    //Log.w("Firestore", "Error al actualizar el documento", e)
                                }
                            //viewModelAlumno.eliminarAlumno(firstInput)
                            //viewModelAlumno.removeAlumnoByEmail(firstInput)
                        }
                    ) {
                        Text("Confirmar")
                    }
                }
            }
        )

    }

    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = Color(0xFF364F59),
            disabledContentColor = Color(0xFF364F59),
            disabledContainerColor = Color.White
        ),
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 0.dp)
            .fillMaxWidth()
            .clickable {
                viewModel.solicitud.value = solicitud
                navController.navigate(route = AppScreens.FormularioSolicitudScreen.route)
            }
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
                    overflow = TextOverflow.Ellipsis
                )

               /* Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = solicitud.nif,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )*/

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = solicitud.coordinador,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                /*
                Spacer(modifier = Modifier.height(4.dp))

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
                )*/

                Spacer(modifier = Modifier.height(16.dp))

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
                        viewModel.setChecked(it)

                        val email = com.google.firebase.ktx.Firebase.auth.currentUser?.email!!

                        if (it) {
                            viewModel.setCoordinador(com.google.firebase.ktx.Firebase.auth.currentUser?.email!!)
                            viewModel.setChecked(true)

                            com.google.firebase.ktx.Firebase.firestore
                                .collection("solicitudes")
                                .document(solicitud.nif)
                                .update("coordinador", email)
                                .addOnSuccessListener {
                                    //Toast.makeText(context, "Coordinador actualizado", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener { e ->
                                    //Log.w("Firestore", "Error al actualizar el documento", e)
                                }

                            com.google.firebase.ktx.Firebase.firestore
                                .collection("solicitudes")
                                .document(solicitud.nif)
                                .update("checked", true)
                                .addOnSuccessListener {
                                    //Toast.makeText(context, "Coordinador actualizado", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener { e ->
                                    //Log.w("Firestore", "Error al actualizar el documento", e)
                                }
                        }
                        else {
                            viewModel.setCoordinador("")
                            viewModel.setChecked(false)

                            com.google.firebase.ktx.Firebase.firestore
                                .collection("solicitudes")
                                .document(solicitud.nif)
                                .update("coordinador", "")
                                .addOnSuccessListener {
                                    //Toast.makeText(context, "Coordinador actualizado", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener { e ->
                                    //Log.w("Firestore", "Error al actualizar el documento", e)
                                }

                            com.google.firebase.ktx.Firebase.firestore
                                .collection("solicitudes")
                                .document(solicitud.nif)
                                .update("checked", false)
                                .addOnSuccessListener {
                                    //Toast.makeText(context, "Coordinador actualizado", Toast.LENGTH_LONG).show()
                                }
                                .addOnFailureListener { e ->
                                    //Log.w("Firestore", "Error al actualizar el documento", e)
                                }
                        }},
                    modifier = Modifier.graphicsLayer(scaleX = 0.8f, scaleY = 0.8f),
                    enabled = hayCoordinador
                )

                IconButton(
                    onClick = { showAddAlumnoDialog = true}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_add), // Icono de Email
                        contentDescription = "Mail Icon"
                    )
                }

                IconButton(
                    onClick = {showDeleteAlumnoDialog = true }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person_remove), // Icono de Email
                        contentDescription = "Mail Icon"
                    )
                }

                IconButton(
                    onClick = {
                        viewModel.removeSolicitudByNif(solicitud.nif)
                        viewModel.eliminarSolicitud(solicitud.nif)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Icon")
                }
            }
        }
    }
}

/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabContent1(navController: NavHostController, nifCompare: String?, viewModel: SolicitudViewModel)
{

    val state = rememberPullToRefreshState()
    var searchText by remember { mutableStateOf("") }
    val solicitudes by viewModel.solicitudes.observeAsState(initial = viewModel.solicitudes.value ?: emptyList())

    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch something
            delay(1500)
            viewModel.añadirSolicitudes()
            state.endRefresh()
        }
    }
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

        /*val solicitud1 = Solicitud(
            nif= "A12345678",
            empresa = "Soluciones Innovadoras S.L.",
            funciones= "Programación App Web",
            horario= "L-V 8-14",
            plazas= LinkedHashMap<String, Int>(),
            estado = "Nueva",
            coordinador = "juan.perez@escuela.edu",
            alumnos = null
        )

        solicitud1.plazas["Sistemas Microinformáticos y Redes G.M."] = 2

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

        solicitud2.plazas["Desarrollo de Aplicaciones Multiplataforma G.S."] = 1*/

        //val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)

        Column (
            modifier = Modifier.fillMaxSize().nestedScroll(state.nestedScrollConnection)
        )
        {
            if (nifCompare.isNullOrEmpty()){
                val filteredItems = remember(searchText) {
                    solicitudes.filter {it.empresa.contains(searchText, ignoreCase = true)
                            || it.funciones.contains(searchText, ignoreCase = true)}}

                LazyColumn {
                    if (!state.isRefreshing) {
                        filteredItems.forEach{ solicitud ->
                            item {
                                SolicitudItem( solicitud= solicitud, navController, viewModel)
                            }
                        }
                    }
                }
            } else{
                val filteredNifItems = remember(nifCompare) {
                    solicitudes.filter { it.nif.equals(nifCompare, ignoreCase = true)}}

                LazyColumn {
                    if (!state.isRefreshing) {
                        filteredNifItems.forEach{ solicitud ->
                            item {
                                SolicitudItem( solicitud= solicitud, navController, viewModel)
                            }
                        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabContent2(navController: NavHostController, nifCompare: String, viewModel: SolicitudViewModel)
{

    val state = rememberPullToRefreshState()
    var searchText by remember { mutableStateOf("") }
    val solicitudes by viewModel.solicitudes.observeAsState(initial = viewModel.solicitudes.value ?: emptyList())

    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch something
            delay(1500)
            viewModel.añadirSolicitudes()
            state.endRefresh()
        }
    }
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

        /*val solicitud1 = Solicitud(
            nif= "A12345678",
            empresa = "Soluciones Innovadoras S.L.",
            funciones= "Programación App Web",
            horario= "L-V 8-14",
            plazas= LinkedHashMap<String, Int>(),
            estado = "Nueva",
            coordinador = "juan.perez@escuela.edu",
            alumnos = null
        )

        solicitud1.plazas["Sistemas Microinformáticos y Redes G.M."] = 2

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

        solicitud2.plazas["Desarrollo de Aplicaciones Multiplataforma G.S."] = 1*/

        //val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)

        Column (
            modifier = Modifier.fillMaxSize().nestedScroll(state.nestedScrollConnection)
        )
        {
            if (nifCompare.isNullOrEmpty()){
                val filteredItems = remember(searchText) {
                    solicitudes.filter {it.empresa.contains(searchText, ignoreCase = true)
                            || it.funciones.contains(searchText, ignoreCase = true)}}

                LazyColumn {
                    if (!state.isRefreshing) {
                        filteredItems.forEach{ solicitud ->
                            item {
                                SolicitudItem( solicitud= solicitud, navController, viewModel)
                            }
                        }
                    }
                }
            } else{
                val filteredNifItems = remember(nifCompare) {
                    solicitudes.filter { it.nif.equals(nifCompare, ignoreCase = true)}}

                LazyColumn {
                    if (!state.isRefreshing) {
                        filteredNifItems.forEach{ solicitud ->
                            item {
                                SolicitudItem( solicitud= solicitud, navController, viewModel)
                            }
                        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabContent3(navController: NavHostController, nifCompare: String, viewModel: SolicitudViewModel)
{

    val state = rememberPullToRefreshState()
    var searchText by remember { mutableStateOf("") }
    val solicitudes by viewModel.solicitudes.observeAsState(initial = viewModel.solicitudes.value ?: emptyList())

    if (state.isRefreshing) {
        LaunchedEffect(true) {
            // fetch something
            delay(1500)
            viewModel.añadirSolicitudes()
            state.endRefresh()
        }
    }
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

        /*val solicitud1 = Solicitud(
            nif= "A12345678",
            empresa = "Soluciones Innovadoras S.L.",
            funciones= "Programación App Web",
            horario= "L-V 8-14",
            plazas= LinkedHashMap<String, Int>(),
            estado = "Nueva",
            coordinador = "juan.perez@escuela.edu",
            alumnos = null
        )

        solicitud1.plazas["Sistemas Microinformáticos y Redes G.M."] = 2

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

        solicitud2.plazas["Desarrollo de Aplicaciones Multiplataforma G.S."] = 1*/

        //val solicitudes: List<Solicitud> = listOf(solicitud1, solicitud2)

        Column (
            modifier = Modifier.fillMaxSize().nestedScroll(state.nestedScrollConnection)
        )
        {
            if (nifCompare.isNullOrEmpty()){
                val filteredItems = remember(searchText) {
                    solicitudes.filter {it.empresa.contains(searchText, ignoreCase = true)
                            || it.funciones.contains(searchText, ignoreCase = true)}}

                LazyColumn {
                    if (!state.isRefreshing) {
                        filteredItems.forEach{ solicitud ->
                            item {
                                SolicitudItem( solicitud= solicitud, navController, viewModel)
                            }
                        }
                    }
                }
            } else{
                val filteredNifItems = remember(nifCompare) {
                    solicitudes.filter { it.nif.equals(nifCompare, ignoreCase = true)}}

                LazyColumn {
                    if (!state.isRefreshing) {
                        filteredNifItems.forEach{ solicitud ->
                            item {
                                SolicitudItem( solicitud= solicitud, navController, viewModel)
                            }
                        }
                    }
                }
            }

            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                state = state
            )
        }

    }
}*/