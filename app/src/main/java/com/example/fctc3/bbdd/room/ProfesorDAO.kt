package com.example.fctc3.bbdd.room

import androidx.room.*
import com.example.fct.models.Profesor

@Dao
interface ProfesorDAO
{
    @Insert
    suspend fun insertarProfesorFirebase(profesor: Profesor)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarProfesor(profesor: Profesor)

    @Query("SELECT * FROM profesores")
    suspend fun obtenerTodosLosProfesores(): List<Profesor>

    @Query("DELETE FROM profesores WHERE email=:email")
    suspend fun eliminarProfesor(email: String)

    @Query("DELETE FROM profesores")
    suspend fun eliminarTodos()

}