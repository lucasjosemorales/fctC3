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
import androidx.navigation.NavController
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
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen(navController: NavController)
{
    val navHostController = rememberNavController()

    Scaffold(
        content = { NavigationGraph(navHostController) },
        topBar = { ExampleTopAppBar() },
        bottomBar = { BottomNavigationContent(navHostController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { OnClickFAB(navHostController, navController) })
            {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Añadir"
                )
            }
        }
    )
}

private fun OnClickFAB(navHostController: NavHostController, navController: NavController)
{
    val currentDestination = navHostController.currentBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    if (currentRoute != null)
    {
        if (currentRoute == NavItem.Empresas.route) {
            navController.navigate(route = AppScreens.FormularioEmpresaScreen.route)
        }

        if (currentRoute == NavItem.Profesores.route) {
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
    val items = listOf(NavItem.Empresas, NavItem.Alumnos, NavItem.Profesores, NavItem.Solicitudes)

    NavigationBar(
        modifier = Modifier.height(72.dp).fillMaxSize()
    )
    {

        val backStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val currentRoute: String? = backStackEntry?.destination?.route

        items.forEach { item: NavItem ->
            NavigationBarItem(
                selected = (currentRoute == item.route),
                icon = ({ Icon(painter = painterResource(id = item.icon), contentDescription = null) }),
                label = { Text(text = item.title) },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}


@Composable
fun NavigationGraph(navController: NavHostController)
{
    Column()
    {

        Spacer(modifier =  Modifier.height(56.dp))

        NavHost(navController = navController, startDestination = NavItem.Empresas.route)
        {
            composable(NavItem.Empresas.route)
            {
                EmpresasScreen(navController)
            }

            composable(AppScreens.EmpresasScreen.route)
            {
                EmpresasScreen(navController)
            }

            composable(NavItem.Profesores.route)
            {
                ProfesoresScreen(navController)
            }

            composable(NavItem.Alumnos.route)
            {
                AlumnosScreen()
            }

            composable(NavItem.Solicitudes.route)
            {
                SolicitudesScreen()
            }

            composable(AppScreens.FormularioSolicitudScreen.route){
                FormularioSolicitudScreen(navController)
            }
        }

        Spacer(modifier =  Modifier.height(72.dp))
    }
}
