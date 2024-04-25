package com.example.fc3.navigation

sealed class AppScreens(val route: String)
{
    object LoginScreen: AppScreens("login_screen")
    object FormularioScreen: AppScreens("formulario_screen")
    object ScaffoldScreen: AppScreens("scaffold_screen")
}