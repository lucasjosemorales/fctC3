package com.example.fc3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fc3.navigation.*
import com.example.fc3.screens.bottom_screens.AlumnosScreen
import com.example.fc3.screens.bottom_screens.EmpresasScreen
import com.example.fc3.screens.bottom_screens.ProfesoresScreen
import com.example.fc3.screens.bottom_screens.SolicitudesScreen
import com.example.fc3.screens.formularios.FormularioAlumnoScreen
import com.example.fc3.screens.formularios.FormularioEmpresaScreen
import com.example.fc3.screens.formularios.FormularioProfesorScreen
import com.example.fc3.screens.formularios.FormularioSolicitudScreen
import com.example.fc3.themes.MyAppTheme
import com.example.fc3.viewmodels.AlumnoViewModel
import com.example.fc3.viewmodels.ProfesorViewModel
import com.example.fctc3.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(navController: NavHostController, viewModel: List<ViewModel>)
{
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showFloatingActionButton = remember(currentRoute) {
        (currentRoute != AppScreens.SolicitudesScreen.route) &&
                (currentRoute != AppScreens.FormularioProfesorScreen.route) &&
                    (currentRoute != AppScreens.FormularioAlumnoScreen.route) &&
                        (currentRoute != AppScreens.FormularioEmpresaScreen.route) &&
                            (currentRoute != AppScreens.FormularioSolicitudScreen.route)

    }

    Scaffold(
        content = {innerPadding -> NavigationGraph(navController, innerPadding, viewModel) },
        topBar = { ExampleTopAppBar(navController) },
        bottomBar = { BottomNavigationContent(navController) },
        floatingActionButton = {
            if (showFloatingActionButton) {
                FloatingActionButton(onClick = {

                    //Esto habrá que hacerlo con todos los viewModel
                    val alumnoViewModel: AlumnoViewModel = viewModel[0] as AlumnoViewModel
                    alumnoViewModel.alumno.value = null


                    OnClickFAB(navController) })
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
        if (currentRoute == AppScreens.EmpresasScreen.route) {
            navController.navigate(route = AppScreens.FormularioEmpresaScreen.route)
        }

        if (currentRoute == AppScreens.ProfesoresScreen.route) {
            navController.navigate(route = AppScreens.FormularioProfesorScreen.route)
        }

        if (currentRoute == AppScreens.AlumnosScreen.route) {
            navController.navigate(route = AppScreens.FormularioAlumnoScreen.route)
        }

    }
}

@Composable
private fun textoTopAppBar()
{
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxSize()
    )
    {
        Text(text = "FCT C3", textAlign = TextAlign.Center)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleTopAppBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showNavigationIconButton = remember(currentRoute) {
        (currentRoute != AppScreens.AlumnosScreen.route) &&
                (currentRoute != AppScreens.EmpresasScreen.route) &&
                (currentRoute != AppScreens.ProfesoresScreen.route) &&
                (currentRoute != AppScreens.SolicitudesScreen.route)}

        TopAppBar(
        title = { textoTopAppBar() },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
                }
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.filtrar), contentDescription = "Ordenar")
                }
                IconButton(onClick = { }) {
                    Icon(painter = painterResource(id = R.drawable.ordenar), contentDescription = "Filtrar")
                }
                /*IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
                }*/
            }
        },
        modifier = Modifier.height(56.dp),
        navigationIcon = {
            if (showNavigationIconButton) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        colors = appBarColors()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun appBarColors() = TopAppBarDefaults.smallTopAppBarColors(
    containerColor = Color.White,
    titleContentColor = Color(0xFF364F59),  // Texto e íconos
    actionIconContentColor = Color(0xFF364F59)
)

@Composable
fun BottomNavigationContent(navController: NavHostController)
{
    val items = listOf(AppScreens.EmpresasScreen, AppScreens.AlumnosScreen, AppScreens.ProfesoresScreen, AppScreens.SolicitudesScreen)

    NavigationBar(
        modifier = Modifier.height(72.dp).fillMaxSize()
    )
    {

        val backStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val currentRoute: String? = backStackEntry?.destination?.route

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
        unselectedTextColor = unselectedTextColor
    )
}


@Composable
fun NavigationGraph(navController: NavHostController, inner: PaddingValues, viewModel: List<ViewModel>)
{
    //val navController = rememberNavController()

    Column(modifier = Modifier.padding(inner))
    {

        val alumnoViewModel: AlumnoViewModel = viewModel[0] as AlumnoViewModel
        val profesorViewModel: ProfesorViewModel = viewModel[1] as ProfesorViewModel

        NavHost(
            navController = navController,
            startDestination = AppScreens.EmpresasScreen.route
        )
        {

            composable(AppScreens.AlumnosScreen.route)
            {

                AlumnosScreen(navController, viewModel = alumnoViewModel)
            }

            composable(AppScreens.EmpresasScreen.route)
            {
                EmpresasScreen(navController)
            }

            composable(AppScreens.ProfesoresScreen.route)
            {
                ProfesoresScreen(navController, viewModel = profesorViewModel)
            }

            composable(AppScreens.SolicitudesScreen.route)
            {
                SolicitudesScreen(navController)
            }

            //Formularios
            composable(route=AppScreens.FormularioEmpresaScreen.route){
                FormularioEmpresaScreen(navController)
            }
            composable(route=AppScreens.FormularioProfesorScreen.route){
                FormularioProfesorScreen(navController, profesorViewModel.profesor.value)
            }
            composable(route=AppScreens.FormularioSolicitudScreen.route){
                FormularioSolicitudScreen(navController)
            }
            composable(route=AppScreens.FormularioAlumnoScreen.route){

                FormularioAlumnoScreen(navController, alumnoViewModel.alumno.value)
            }

        }

        Spacer(modifier =  Modifier.height(72.dp))
    }
}
