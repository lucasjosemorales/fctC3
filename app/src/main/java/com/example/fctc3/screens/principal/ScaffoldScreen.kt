package com.example.fctc3.screens.principal

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fc3.viewmodels.LoginViewModel
import com.example.fctc3.navigation.*
import com.example.fctc3.screens.bottom_screens.*
import com.example.fctc3.screens.formularios.FormularioAlumnoScreen
import com.example.fctc3.screens.formularios.FormularioEmpresaScreen
import com.example.fctc3.screens.formularios.FormularioProfesorScreen
import com.example.fctc3.screens.formularios.FormularioSolicitudScreen
import com.example.fctc3.viewmodels.screens.AlumnoViewModel
import com.example.fctc3.viewmodels.screens.EmpresaViewModel
import com.example.fctc3.viewmodels.screens.ProfesorViewModel
import com.example.fctc3.viewmodels.screens.SolicitudViewModel
import com.example.fct.models.Profesor
import com.example.fctc3.R
import com.example.fctc3.bbdd.Firebase
import com.example.fctc3.screens.login.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun BackHandlerWithConfirmation(onConfirmBack: () -> Unit, onDismiss: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    // Manejar la acción de retroceso
    BackHandler(enabled = true) {
        showDialog = true
    }

    // Si showDialog es verdadero, mostrar el diálogo de confirmación
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Esto se llama cuando el usuario toca fuera del diálogo o presiona el botón de retroceso.
                showDialog = false
                onDismiss()
            },
            title = { Text("Logout") },
            text = { Text("¿Estás seguro de que quieres salir?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onConfirmBack() // Confirmar y manejar el retroceso como se desee.
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun SetStatusBarColor() {
    val context = LocalContext.current
    val activity = context as? Activity
    activity?.window?.let { window ->
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = true
        //window.statusBarColor = android.graphics.Color.parseColor("#364F59")
        window.statusBarColor = android.graphics.Color.WHITE;
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(navControllerPrincipal: NavHostController, viewModel: List<ViewModel>)
{

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showFloatingActionButton = remember(currentRoute) {
        (currentRoute == AppScreens.EmpresasScreen.route) || (currentRoute == AppScreens.AlumnosScreen.route)
    }

    val showNavigationContent = remember(currentRoute) {
        (currentRoute != AppScreens.FormularioProfesorScreen.route) &&
                (currentRoute != AppScreens.FormularioAlumnoScreen.route) &&
                (currentRoute != AppScreens.FormularioEmpresaScreen.route) &&
                (currentRoute != AppScreens.FormularioSolicitudScreen.route) &&
                (currentRoute != AppScreens.LoginScreen.route) &&
                (currentRoute != AppScreens.ForgotScreen.route)
    }

    val showTopAppBar = remember(currentRoute)
    {
        (currentRoute != AppScreens.LoginScreen.route)
    }

    BackHandlerWithConfirmation(
        onConfirmBack = {
            navControllerPrincipal.popBackStack()
        },
        onDismiss = {
            // Manejo opcional cuando se descarta el diálogo
        }
    )

    SetStatusBarColor()

    Scaffold(

        content = { innerPadding -> NavigationGraph(navController, innerPadding, viewModel) },
        topBar = {
                    if (showTopAppBar)
                        TopAppBar(navController = navController, pantalla = currentRoute ?: "",
                            navControllerPrincipal = navControllerPrincipal)
                 },
        bottomBar = {
                        if (showNavigationContent)
                        {
                            BottomNavigationContent(navController)
                        }

                    },
        floatingActionButton = {

            if (showFloatingActionButton) {
                FloatingActionButton(
                    contentColor = Color(0xFF364F59),
                    containerColor = Color(0xFF647C87),
                    onClick = {

                        //showDialog = true

                        //Esto habrá que hacerlo con todos los viewModel
                        val alumnoViewModel: AlumnoViewModel = viewModel[0] as AlumnoViewModel
                        alumnoViewModel.alumno.value = null

                        val profesorViewModel: ProfesorViewModel = viewModel[1] as ProfesorViewModel
                        profesorViewModel.profesor.value = null

                        val empresaViewModel: EmpresaViewModel = viewModel[2] as EmpresaViewModel
                        empresaViewModel.empresa.value = null

                        OnClickFAB(navController)
                    }
                )
                {
                    Icon(Icons.Filled.Add, contentDescription = "Add")
                }
            }
        },

    )
}

private fun OnClickFAB(navController: NavHostController)
{
    val currentDestination = navController.currentBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    if (currentRoute != null)
    {
        if (currentRoute == AppScreens.EmpresasScreen.route)
        {
            navController.navigate(route = AppScreens.FormularioEmpresaScreen.route)
        }

        if (currentRoute == AppScreens.ProfesoresScreen.route)
        {
            navController.navigate(route = AppScreens.FormularioProfesorScreen.route)
        }

        if (currentRoute == AppScreens.AlumnosScreen.route)
        {
            navController.navigate(route = AppScreens.FormularioAlumnoScreen.route)
        }
    }
}

@Composable
fun textoTopAppBar(pantalla: String)
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    )
    {
        Text(text = pantalla, textAlign = TextAlign.Center)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(navController: NavHostController, pantalla: String, navControllerPrincipal: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    //No se muestra si es una BottomScreen
    val showNavigationIconButton = remember(currentRoute) {
        (currentRoute != AppScreens.AlumnosScreen.route) &&
                (currentRoute != AppScreens.EmpresasScreen.route) &&
                (currentRoute != AppScreens.ProfesoresScreen.route) &&
                (currentRoute != AppScreens.SolicitudesScreen.route) &&
                (currentRoute != AppScreens.AdminScreen.route)
    }

    //Se muestra solo para las BottomScreens
    val showActions = remember(currentRoute) {
        (currentRoute == AppScreens.AlumnosScreen.route) ||
                (currentRoute == AppScreens.EmpresasScreen.route) ||
                (currentRoute == AppScreens.ProfesoresScreen.route) ||
                (currentRoute == AppScreens.SolicitudesScreen.route) ||
                (currentRoute == AppScreens.AdminScreen.route)
    }

    TopAppBar(
        title = { textoTopAppBar(pantalla) },
        modifier = Modifier.height(56.dp),
        navigationIcon = {
            if (showNavigationIconButton)
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        colors = appBarColors(),
        actions = {
            if (showActions)
            {
                IconButton(
                    onClick = {
                        //Cerramos sesión en Firebase
                        val user = FirebaseAuth.getInstance()
                        user.signOut()
                        //Nos vamos a la pantalla de Login
                        navControllerPrincipal.navigate(AppScreens.LoginScreen.route)
                    }
                )
                {
                    Icon(painterResource(R.drawable.logout), contentDescription = "Logout")
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appBarColors() = TopAppBarDefaults.topAppBarColors(
    containerColor = Color.White,
    titleContentColor = Color(0xFF364F59),  // Texto e íconos
    actionIconContentColor = Color(0xFF364F59)
)

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BottomNavigationContent(navController: NavHostController)
{
    val items = listOf(AppScreens.EmpresasScreen, AppScreens.AlumnosScreen, AppScreens.ProfesoresScreen, AppScreens.SolicitudesScreen)
    val itemsAdmin = listOf(AppScreens.EmpresasScreen, AppScreens.AlumnosScreen, AppScreens.ProfesoresScreen, AppScreens.SolicitudesScreen, AppScreens.AdminScreen)

    NavigationBar(
        modifier = Modifier.height(72.dp).fillMaxSize()
    )
    {

        val backStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val currentRoute: String? = backStackEntry?.destination?.route
        val user = FirebaseAuth.getInstance().currentUser
        val auth = Firebase(LocalContext.current)

        val loginViewModel: LoginViewModel = viewModel()
        val isAdmin by loginViewModel.isAdmin.observeAsState(initial = false)

        if (user != null) {
            loginViewModel.checkIfUserIsAdmin(user.email!!)
        }

        when (isAdmin) {
            null -> CircularProgressIndicator()  // Mostrar barra de progreso mientras carga
            true ->
                itemsAdmin.forEach { item: AppScreens ->
                val isSelected = (item.route == currentRoute)
                NavigationBarItem(
                    selected = (currentRoute == item.route),
                    icon = ({ Icon(painter = painterResource(if (isSelected) item.selectedIcon else item.icon), contentDescription = null) }),
                    label = { Text(text = item.title) },
                    onClick = {
                        navController.navigate(item.route){
                            // Aquí puedes agregar lógicas de navegación como limpiar el backstack o evitar múltiples copias
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = customNavigationBarItemColors()
                )
            }
            false ->
                items.forEach { item: AppScreens ->
                    val isSelected = (item.route == currentRoute)
                    NavigationBarItem(
                        selected = (currentRoute == item.route),
                        icon = ({ Icon(painter = painterResource(if (isSelected) item.selectedIcon else item.icon), contentDescription = null) }),
                        label = { Text(text = item.title) },
                        onClick = {
                            navController.navigate(item.route){
                                // Aquí puedes agregar lógicas de navegación como limpiar el backstack o evitar múltiples copias
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = customNavigationBarItemColors()
                    )
                }
        }

        /*Sí es admin
        if (isAdmin!!)
        {
            itemsAdmin.forEach { item: AppScreens ->
                val isSelected = (item.route == currentRoute)
                NavigationBarItem(
                    selected = (currentRoute == item.route),
                    icon = ({ Icon(painter = painterResource(if (isSelected) item.selectedIcon else item.icon), contentDescription = null) }),
                    label = { Text(text = item.title) },
                    onClick = {
                        navController.navigate(item.route){
                            // Aquí puedes agregar lógicas de navegación como limpiar el backstack o evitar múltiples copias
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = customNavigationBarItemColors()
                )
            }
        }
        //No es Admin
        else
        {
            items.forEach { item: AppScreens ->
                val isSelected = (item.route == currentRoute)
                NavigationBarItem(
                    selected = (currentRoute == item.route),
                    icon = ({ Icon(painter = painterResource(if (isSelected) item.selectedIcon else item.icon), contentDescription = null) }),
                    label = { Text(text = item.title) },
                    onClick = {
                        navController.navigate(item.route){
                            // Aquí puedes agregar lógicas de navegación como limpiar el backstack o evitar múltiples copias
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    colors = customNavigationBarItemColors()
                )
            }
        }*/


    }
}

// Función para crear los colores de los ítems
@Composable
fun customNavigationBarItemColors(): NavigationBarItemColors {

    // Definir los colores
    val selectedIconColor = Color(0xFF364F59) // Color seleccionado
    val unselectedIconColor = Color(0xFF647C87) // Color no seleccionado más claro
    val selectedTextColor = selectedIconColor // Usar el mismo color para el texto seleccionado
    val unselectedTextColor = unselectedIconColor // Usar el color no seleccionado para el texto

    return NavigationBarItemDefaults.colors(
        selectedIconColor = selectedIconColor,
        unselectedIconColor = unselectedIconColor,
        selectedTextColor = selectedTextColor,
        unselectedTextColor = unselectedTextColor,
        indicatorColor = Color(0xFF647C87)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationGraph(navController: NavHostController, inner: PaddingValues, viewModel: List<ViewModel>)
{
    //val navController = rememberNavController()

    Column(modifier = Modifier.padding(inner))
    {

        val alumnoViewModel: AlumnoViewModel = viewModel[0] as AlumnoViewModel
        val profesorViewModel: ProfesorViewModel = viewModel[1] as ProfesorViewModel
        val empresaViewModel: EmpresaViewModel = viewModel[2] as EmpresaViewModel
        val solicitudViewModel: SolicitudViewModel = viewModel[3] as SolicitudViewModel

        NavHost(
            navController = navController,
            startDestination = AppScreens.EmpresasScreen.route
        )
        {
            //LoginScreen
            composable(AppScreens.LoginScreen.route)
            {
                LoginScreen(navController)
            }

            composable(AppScreens.AlumnosScreen.route)
            {
                AlumnosScreen(navController, viewModel = alumnoViewModel)
            }

            composable(AppScreens.EmpresasScreen.route)
            {
                EmpresasScreen(navController, viewModel = empresaViewModel)
            }

            composable(AppScreens.ProfesoresScreen.route)
            {
                ProfesoresScreen(navController, viewModel = profesorViewModel)
            }

            composable(AppScreens.SolicitudesScreen.route)
            {
                SolicitudesScreen(navController, empresaViewModel, solicitudViewModel)
            }

            //Formularios
            composable(route=AppScreens.FormularioEmpresaScreen.route){
                FormularioEmpresaScreen(navController, empresaViewModel.empresa.value)
            }
            composable(route=AppScreens.FormularioProfesorScreen.route){
                FormularioProfesorScreen(navController, profesorViewModel.profesor.value!!)
            }
            composable(route=AppScreens.FormularioSolicitudScreen.route){
                FormularioSolicitudScreen(navController, solicitudViewModel.solicitud.value)
            }
            composable(route=AppScreens.FormularioAlumnoScreen.route){

                FormularioAlumnoScreen(navController, alumnoViewModel.alumno.value)
            }
            composable(route=AppScreens.AdminScreen.route){
                AdminScreen(navController, profesorViewModel)
            }

        }

        Spacer(modifier =  Modifier.height(72.dp))
    }
}
