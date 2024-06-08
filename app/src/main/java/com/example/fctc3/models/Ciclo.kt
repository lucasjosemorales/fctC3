package com.example.fctc3.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ciclos")
data class Ciclo(
    @PrimaryKey
    val nombreCorto: String,
    @ColumnInfo
    val nombreLargo: String,
    @ColumnInfo
    val familia: String
)