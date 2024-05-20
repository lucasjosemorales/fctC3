package com.example.fc3.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fc3.screens.*
import com.example.fc3.screens.bottom_screens.AlumnosScreen
import com.example.fc3.screens.bottom_screens.EmpresasScreen
import com.example.fc3.screens.bottom_screens.ProfesoresScreen
import com.example.fc3.screens.bottom_screens.SolicitudesScreen
import com.example.fc3.screens.formularios.FormularioAlumnoScreen
import com.example.fc3.screens.formularios.FormularioEmpresaScreen
import com.example.fc3.screens.formularios.FormularioProfesorScreen
import com.example.fc3.screens.formularios.FormularioSolicitudScreen
import com.example.fc3.screens.login.ForgotScreen
import com.example.fc3.screens.login.LoginScreen
import com.example.fc3.viewmodels.AlumnoViewModel
import com.example.fc3.viewmodels.AlumnoViewModelFactory
import com.example.fct.models.Alumno


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation()
{
    val navController = rememberNavController()
    val viewModel: AlumnoViewModel = viewModel()

    NavHost(navController=navController, startDestination=AppScreens.LoginScreen.route)
    {
        //Login
        composable(route=AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route=AppScreens.ForgotScreen.route){
            ForgotScreen(navController)
        }

        //Pantalla principal
        composable(route=AppScreens.ScaffoldScreen.route){
            ScaffoldScreen(navController, viewModel)
        }

        //Formularios
        composable(route=AppScreens.FormularioEmpresaScreen.route){
            FormularioEmpresaScreen(navController)
        }
        composable(route=AppScreens.FormularioProfesorScreen.route){
            FormularioProfesorScreen(navController)
        }
        composable(route = "AppScreens.FormularioAlumnoScreen")
        {
            FormularioAlumnoScreen(navController, viewModel.alumno.value)
        }
        composable(route=AppScreens.FormularioSolicitudScreen.route){
            FormularioSolicitudScreen(navController)
        }

        //BottomScreens
        composable(route=AppScreens.AlumnosScreen.route){
            AlumnosScreen(navController, viewModel)
        }
        composable(route=AppScreens.EmpresasScreen.route){
            EmpresasScreen(navController)
        }
        composable(route=AppScreens.ProfesoresScreen.route){
            ProfesoresScreen(navController)
        }
        composable(route=AppScreens.SolicitudesScreen.route){
            SolicitudesScreen(navController)
        }

    }

}