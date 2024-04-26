package com.example.fc3.navigation

import com.example.fctc3.R

sealed class NavItem(
    var title:String,
    var icon: Int,
    var route: String
)
{
    object Empresas: NavItem("Empresas", R.drawable.empresas, "route_empresas")
    object Profesores: NavItem("Profesores", R.drawable.profesores, "route_profesores")
    object Alumnos: NavItem("Alumnos", R.drawable.alumnos, "route_alumnos")
    object Solicitudes: NavItem("Solicitudes", R.drawable.solicitudes, "route_solicitudes")
}