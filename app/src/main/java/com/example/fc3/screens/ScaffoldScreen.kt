package com.example.fc3.screens

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
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

        if (currentRoute == NavItem.Solicitudes.route) {
            navController.navigate(route = AppScreens.FormularioSolicitudScreen.route)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ExampleTopAppBar() {
    TopAppBar(
        title = { Text(text = "FCT C3") },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar")
            }
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.filtrar), contentDescription = "Ordenar")
            }
            IconButton(onClick = { }) {
                Icon(painter = painterResource(id = R.drawable.ordenar), contentDescription = "Filtrar")
            }
            IconButton(onClick = {  }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Más")
            }
        }
    )
}

@Composable
fun BottomNavigationContent(navController: NavHostController)
{
    val items = listOf(NavItem.Empresas, NavItem.Alumnos, NavItem.Profesores, NavItem.Solicitudes)

    NavigationBar{

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
    NavHost(navController= navController, startDestination = NavItem.Empresas.route)
    {
        composable(NavItem.Empresas.route)
        {
            EmpresasScreen()
        }

        composable(NavItem.Profesores.route)
        {
            ProfesoresScreen()
        }

        composable(NavItem.Alumnos.route)
        {
            AlumnosScreen()
        }

        composable(NavItem.Solicitudes.route)
        {
            SolicitudesScreen()
        }
    }
}
