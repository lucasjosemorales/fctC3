package com.example.fc3.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fc3.navigation.*
import com.example.fctc3.R
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.fc3.screens.bottom_screens.AlumnosScreen
import com.example.fc3.screens.bottom_screens.EmpresasScreen
import com.example.fc3.screens.bottom_screens.ProfesoresScreen
import com.example.fc3.screens.bottom_screens.SolicitudesScreen
import com.example.fc3.screens.formularios.FormularioEmpresaScreen
import com.example.fc3.screens.formularios.FormularioProfesorScreen
import com.example.fc3.screens.formularios.FormularioSolicitudScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(navController: NavHostController)
{
    val navController = rememberNavController()

    Scaffold(
        content = {innerPadding -> NavigationGraph(navController, innerPadding) },
        topBar = { ExampleTopAppBar() },
        bottomBar = { BottomNavigationContent(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { OnClickFAB(navController) })
            {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Añadir"
                )
            }
        }
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
private fun ExampleTopAppBar() {
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
        modifier = Modifier.height(56.dp).fillMaxSize()
    )
}

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
            NavigationBarItem(
                selected = (currentRoute == item.route),
                icon = ({ Icon(painter = painterResource(id = item.icon), contentDescription = null) }),
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
                })
        }
    }
}


@Composable
fun NavigationGraph(navController: NavHostController, inner: PaddingValues)
{
    //val navController = rememberNavController()

    Column(modifier = Modifier.padding(inner))
    {

        Spacer(modifier =  Modifier.height(56.dp))

       /* when(itemClick)
        {
            AppScreens.EmpresasScreen.route -> EmpresasScreen(navController)
            AppScreens.AlumnosScreen.route -> AlumnosScreen(navController)
            AppScreens.ProfesoresScreen.route -> ProfesoresScreen(navController)
            AppScreens.SolicitudesScreen.route -> SolicitudesScreen(navController)
            else -> EmpresasScreen(navController)

        }*/

        NavHost(
            navController = navController,
            startDestination = AppScreens.EmpresasScreen.route
        )
        {

            composable(AppScreens.AlumnosScreen.route)
            {
                AlumnosScreen(navController)
            }

            composable(AppScreens.EmpresasScreen.route)
            {
                EmpresasScreen(navController)
            }

            composable(AppScreens.ProfesoresScreen.route)
            {
                ProfesoresScreen(navController)
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
                FormularioProfesorScreen(navController)
            }
            composable(route=AppScreens.FormularioSolicitudScreen.route){
                FormularioSolicitudScreen(navController)
            }
        }

        Spacer(modifier =  Modifier.height(72.dp))
    }
}
