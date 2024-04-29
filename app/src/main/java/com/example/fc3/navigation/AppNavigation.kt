package com.example.fc3.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fc3.navigation.*
import com.example.fc3.screens.FormularioEmpresaScreen
import com.example.fc3.screens.LoginScreen
import com.example.fc3.screens.FormularioSolicitudScreen
import com.example.fc3.screens.ScaffoldScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation()
{
    val navController = rememberNavController()
    NavHost(navController=navController, startDestination=AppScreens.LoginScreen.route)
    {
        composable(route=AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route=AppScreens.ScaffoldScreen.route){
            ScaffoldScreen(navController)
        }
        composable(route=AppScreens.FormularioEmpresaScreen.route){
            FormularioEmpresaScreen(navController)
        }
        composable(route=AppScreens.FormularioSolicitudScreen.route){
            FormularioSolicitudScreen(navController)
        }
    }

}