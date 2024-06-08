package com.example.fctc3.bbdd.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fct.models.Profesor
import com.example.fctc3.models.Ciclo

@Dao
interface CicloDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCiclo(ciclo: Ciclo)

    @Query("SELECT * FROM ciclos")
    suspend fun obtenerTodosLosCiclos(): List<Ciclo>

    @Query("SELECT * FROM ciclos WHERE familia=:familia")
    suspend fun obtenerTodosLosCiclosFamilia(familia: String): List<Ciclo>

    @Query("DELETE FROM ciclos WHERE nombreCorto=:nombreCorto")
    suspend fun eliminarCiclo(nombreCorto: String)
}