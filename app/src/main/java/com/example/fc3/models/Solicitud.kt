package com.example.fc3.models

import com.example.fct.models.Profesor

data class Solicitud(
    val nif: String = "",
    val name: String = "",
    val funciones: String = "",
    val horario: String = "",
    val alumnos: LinkedHashMap<String, Int>,
    val estado: String/*,
    val coordinador: String --> Key,
    val alumnos: MutableList<String> --> Key*/
)