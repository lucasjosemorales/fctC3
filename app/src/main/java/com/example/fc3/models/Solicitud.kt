package com.example.fc3.models

data class Solicitud(
    val nif: String = "",
    val name: String = "",
    val funciones: String = "",
    val horario: String = "",
    val alumnos: LinkedHashMap<String, Int>
)