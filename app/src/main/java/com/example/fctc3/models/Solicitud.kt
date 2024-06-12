package com.example.fctc3.models

data class Solicitud(

    val nif: String = "",
    //¿Rellenar automáticamente?
    val convocatoria: String = "Marzo",
    //¿Rellenar automáticamente?
    val curso: String = "2023-2024",
    val empresa: String = "",
    val funciones: String = "",
    val horario: String = "",
    val plazas: MutableMap<String, Long> = mutableMapOf(),
    val estado: String = "Nueva",
    //Email
    val coordinador: String = "",
    //Email
    val alumnos: MutableList<String> = mutableListOf(),
    val checked: Boolean
)