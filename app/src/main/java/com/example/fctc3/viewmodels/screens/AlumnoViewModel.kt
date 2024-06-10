package com.example.fctc3.viewmodels.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.fct.models.Alumno
import com.example.fct.models.Profesor
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AlumnoViewModel: ViewModel()
{
    //Firebase
    private val dbFirebase = FirebaseFirestore.getInstance()

    private val _alumnos = MutableStateFlow<List<Alumno>>(emptyList())
    val alumnos = _alumnos.asStateFlow().asLiveData()

    fun addAlumno(alumno: Alumno) {
        _alumnos.value = _alumnos.value + alumno
    }

    fun removeAlumnoByEmail(email: String)
    {
        _alumnos.value = _alumnos.value.filter { alumno ->
            alumno.email != email
        }
    }

    init
    {
        añadirAlumnos()
    }

    /*suspend fun cargarProfesores()
    {
        _profesores.value = dao.obtenerTodosLosProfesores()
    }*/


    //Añadir un nuevo profesor
    fun añadirAlumno(alumno: Alumno)
    {
        //dao.insertarProfesor(profesor)
        val alumno = Alumno(
            name = alumno.name,
            email = alumno.email,
            phoneNumber = alumno.phoneNumber,
            grupo = alumno.grupo
            )

        dbFirebase.collection("alumnos").document(alumno.email).set(alumno)
    }

    @SuppressLint("SuspiciousIndentation")
    fun añadirAlumnos()
    {
        _alumnos.value = listOf()
        dbFirebase.collection("alumnos")
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.mapNotNull { document ->

                    val aux = Alumno(
                        name = document.get("name") as String,
                        email = document.get("email") as String,
                        phoneNumber = document.get("phoneNumber") as String,
                        grupo = document.get("grupo") as String
                    )

                    addAlumno(aux)
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors appropriately
            }
    }

    //Eliminar un profesor por su email
    fun eliminarAlumno(email: String)
    {
        dbFirebase.collection("alumnos").document(email).delete()
    }



    /* --- FIN FIREBASE --- */

    var alumno = mutableStateOf<Alumno?>(null)

    private val _name = MutableLiveData<String>()
    val name : LiveData<String> = _name

    fun setName(name: String) {
        _name.value = name
    }

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    fun setEmail(email: String) {
        _email.value = email
    }

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber : LiveData<String> = _phoneNumber

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }

    private val _grupo = MutableLiveData<String>()
    val grupo : LiveData<String> = _grupo

    fun setGrupo(grupo: String) {
        _grupo.value = grupo
    }
}