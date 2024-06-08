package com.example.fctc3.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Grupo(
    @PrimaryKey
    var nombreCorto: String = "",
    @ColumnInfo
    var nombreLargo: String = ""
)