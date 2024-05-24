package com.example.fc3.models

data class Empresa(
    val nif: String = "",
    val name: String = "",
    val localidad: String = "",
    val personaContacto: String = "",
    val tfnoContacto: String = "",
    val email: String = "",
    val repetidor: Boolean = false,
    val contratar: Boolean = false,
    val dual: Boolean = false,
    val observaciones: String = ""
)
