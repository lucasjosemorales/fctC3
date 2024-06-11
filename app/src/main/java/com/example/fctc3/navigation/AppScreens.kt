package com.example.fctc3.navigation

import com.example.fctc3.R

sealed class AppScreens(val route: String, var title:String, var icon: Int, var selectedIcon: Int)
{
    //Login
    object LoginScreen: AppScreens("login_screen", "", 0, 0)
    object ForgotScreen: AppScreens("forgot_screen", "", 0, 0)

    //Pantalla principal
    object ScaffoldScreen: AppScreens("scaffold_screen", "", 0, 0)

    //Formularios
    object FormularioEmpresaScreen: AppScreens("FORMULARIO EMPRESA", "", 0, 0)
    object FormularioSolicitudScreen: AppScreens("FORMULARIO SOLICITUD", "", 0, 0)
    object FormularioProfesorScreen: AppScreens("FORMULARIOS PROFESOR", "", 0, 0)
    object FormularioAlumnoScreen: AppScreens("FORMULARIO ALUMNO", "", 0, 0)

    //BottomScreens
    object AlumnosScreen: AppScreens("ALUMNOS", "Alumnos", R.drawable.alumnos_outline, R.drawable.alumnos)
    object EmpresasScreen: AppScreens("EMPRESAS", "Empresas", R.drawable.empresas_outline, R.drawable.empresas)
    object ProfesoresScreen: AppScreens("PROFESORES", "Profesores", R.drawable.profesores_outline,  R.drawable.profesores)
    object SolicitudesScreen: AppScreens("SOLICITUDES", "Solicitudes", R.drawable.solicitudes_outline, R.drawable.solicitudes)
    object AdminScreen: AppScreens("ADMIN", "Admin", R.drawable.admin_outline, R.drawable.admin)

}
