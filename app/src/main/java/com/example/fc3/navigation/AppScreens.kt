package com.example.fc3.navigation

sealed class AppScreens(val route: String)
{
    object LoginScreen: AppScreens("login_screen")
    object ScaffoldScreen: AppScreens("scaffold_screen")
    object FormularioEmpresaScreen: AppScreens("formulario_empresa_screen")
    object FormularioSolicitudScreen: AppScreens("formulario_solicitud_screen")
    object ForgotScreen: AppScreens("forgot_screen")
}