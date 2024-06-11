package com.example.fct.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profesores")
data class Profesor(
    @PrimaryKey
    var email: String = "",
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String = "",
    @ColumnInfo(name = "tutoria")
    val tutoria: String = "",
    @ColumnInfo(name = "admin")
    val admin: Boolean = false,
    @ColumnInfo(name = "activo")
    val activo: Boolean = true
)
