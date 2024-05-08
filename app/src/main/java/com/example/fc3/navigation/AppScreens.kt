package com.example.fc3.navigation

import com.example.fctc3.R

sealed class AppScreens(val route: String, var title:String,
                        var icon: Int)
{
    //Login
    object LoginScreen: AppScreens("login_screen", "", 0)
    object ForgotScreen: AppScreens("forgot_screen", "", 0)

    //Pantalla principal
    object ScaffoldScreen: AppScreens("scaffold_screen", "", 0)

    //Formularios
    object FormularioEmpresaScreen: AppScreens("formulario_empresa_screen", "", 0)
    object FormularioSolicitudScreen: AppScreens("formulario_solicitud_screen", "", 0)
    object FormularioProfesorScreen: AppScreens("formulario_profesor_screen", "", 0)

    //BottomScreens
    object AlumnosScreen: AppScreens("alumnos_screen", "Alumnos", R.drawable.alumnos)
    object EmpresasScreen: AppScreens("empresas_screen", "Empresas", R.drawable.empresas)
    object ProfesoresScreen: AppScreens("profesores_screen", "Profesores", R.drawable.profesores)
    object SolicitudesScreen: AppScreens("solicitudes_screen", "Solicitudes", R.drawable.solicitudes)

}