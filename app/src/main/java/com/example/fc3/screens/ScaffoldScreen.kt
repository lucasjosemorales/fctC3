package com.example.fc3.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            FloatingActionButton(onClick = { navController.navigate(route = AppScreens.FormularioScreen.route)})
            {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Añadir"
                )
            }
        }
    )
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
