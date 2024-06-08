package com.example.fctc3.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fctc3.screens.bottom_screens.*
import com.example.fctc3.screens.formularios.FormularioAlumnoScreen
import com.example.fctc3.screens.formularios.FormularioEmpresaScreen
import com.example.fctc3.screens.formularios.FormularioProfesorScreen
import com.example.fctc3.screens.formularios.FormularioSolicitudScreen
import com.example.fctc3.screens.login.ForgotScreen
import com.example.fctc3.screens.login.LoginScreen
import com.example.fctc3.screens.principal.ScaffoldScreen
import com.example.fctc3.viewmodels.screens.AlumnoViewModel
import com.example.fctc3.viewmodels.screens.EmpresaViewModel
import com.example.fctc3.viewmodels.screens.ProfesorViewModel
import com.example.fctc3.viewmodels.screens.SolicitudViewModel
import androidx.navigation.compose.composable as composable1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation()
{
    /* ViewModels para el paso entre pantallas */
    val alumnoViewModel: AlumnoViewModel = viewModel()
    val profesorViewModel: ProfesorViewModel = viewModel()
    val empresaViewModel: EmpresaViewModel = viewModel()
    val solicitudViewModel: SolicitudViewModel = viewModel()
    val listaVM: List<ViewModel> = listOf(alumnoViewModel, profesorViewModel, empresaViewModel, solicitudViewModel)

    val navController = rememberNavController()


    NavHost(navController=navController, startDestination=AppScreens.LoginScreen.route)
    {
        //Login
        composable1(route=AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable1(route=AppScreens.ForgotScreen.route){
            ForgotScreen(navController)
        }

        //Pantalla principal
        composable1(route=AppScreens.ScaffoldScreen.route){
            ScaffoldScreen(navController, listaVM)
        }

        //Formularios
        composable1(route=AppScreens.FormularioEmpresaScreen.route){
            FormularioEmpresaScreen(navController, empresaViewModel.empresa.value)
        }
        composable1(route=AppScreens.FormularioProfesorScreen.route){
            FormularioProfesorScreen(navController, profesorViewModel.profesor.value)
        }
        composable1(route = "AppScreens.FormularioAlumnoScreen")
        {
            FormularioAlumnoScreen(navController, alumnoViewModel.alumno.value)
        }
        composable1(route=AppScreens.FormularioSolicitudScreen.route){
            FormularioSolicitudScreen(navController, solicitudViewModel.solicitud.value)
        }

        //BottomScreens
        composable1(route=AppScreens.AlumnosScreen.route){
            AlumnosScreen(navController, alumnoViewModel)
        }
        composable1(route=AppScreens.EmpresasScreen.route){
            EmpresasScreen(navController, empresaViewModel)
        }
        composable1(route=AppScreens.ProfesoresScreen.route){
            ProfesoresScreen(navController, profesorViewModel)
        }
        composable1(route=AppScreens.SolicitudesScreen.route){
            SolicitudesScreen(navController, empresaViewModel, solicitudViewModel)
        }
        composable(route=AppScreens.AdminScreen.route){
            AdminScreen(navController, profesorViewModel)
        }

    }

}