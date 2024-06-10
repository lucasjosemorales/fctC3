package com.example.fctc3.viewmodels.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.fctc3.bbdd.room.AppDatabase
import com.example.fct.models.Profesor
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



class ProfesorViewModel(application: Application) : AndroidViewModel(application)
{
    //Firebase
    private val dbFirebase = FirebaseFirestore.getInstance()

    /*Room
    private val db = AppDatabase.getDatabase(application)
    private val dao = db.profesorDao()*/

    private val _profesores = MutableStateFlow<List<Profesor>>(emptyList())
    val profesores = _profesores.asStateFlow().asLiveData()

    fun addProfesor(profesor: Profesor) {
        _profesores.value = _profesores.value + profesor
    }

    fun removeProfesorByEmail(email: String)
    {
        _profesores.value = _profesores.value.filter { profesor ->
            profesor.email != email
        }
    }

    init
    {
        añadirProfesores()
    }

    /*suspend fun cargarProfesores()
    {
        _profesores.value = dao.obtenerTodosLosProfesores()
    }*/


    //Añadir un nuevo profesor
    fun añadirProfesor(profesor: Profesor)
    {
        //dao.insertarProfesor(profesor)
        val profesor = Profesor(
                                email = profesor.email,
                                name = profesor.name,
                                phoneNumber =  profesor.phoneNumber,
                                tutoria = profesor.tutoria,
                                admin = profesor.admin,
                                activo = profesor.activo)

        dbFirebase.collection("profesores").document(profesor.email).set(profesor)
        //dao.insertarProfesor(profesor)

    }

    @SuppressLint("SuspiciousIndentation")
    fun añadirProfesores()
    {
        _profesores.value = listOf()
        dbFirebase.collection("profesores")
            .get()
            .addOnSuccessListener { snapshot ->
                    snapshot.documents.mapNotNull { document ->

                            val aux = Profesor(
                                email = document.get("email") as String,
                                name = document.get("name") as String,
                                phoneNumber = document.get("phoneNumber") as String,
                                tutoria = document.get("tutoria") as String,
                                admin = document.get("admin") as Boolean,
                                activo = document.get("activo") as Boolean
                            )

                            addProfesor(aux)
                            //dao.insertarProfesor(aux)
                        }
                }
            .addOnFailureListener { exception ->
                // Handle any errors appropriately
            }
    }

    //Eliminar todos
    /*fun eliminarTodos()
    {
        viewModelScope.launch {
            dao.eliminarTodos()
        }
    }*/

    //Eliminar un profesor por su email
    fun eliminarProfesor(email: String)
    {
        dbFirebase.collection("profesores").document(email).delete()
        //dao.eliminarProfesor(email)
    }

    /*-------------- FIN ROOM Y FIREBASE ----------------------------------------*/

    var profesor = mutableStateOf<Profesor?>(Profesor())

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

    private val _tutoria = MutableLiveData<String>()
    val tutoria : LiveData<String> = _tutoria

    fun setTutoria(tutoria: String) {
        _tutoria.value = tutoria
    }

    private val _admin = MutableLiveData<Boolean>()
    val admin: LiveData<Boolean> = _admin

    fun setAdmin(admin: Boolean) {
        _admin.value = admin
    }

    private val _activo = MutableLiveData<Boolean>()
    val activo: LiveData<Boolean> = _activo

    fun setActivo(activo: Boolean) {
        _admin.value = activo
    }


    private val _expanded = MutableLiveData<Boolean>()
    val expanded : LiveData<Boolean> = _expanded

    fun setExpanded(expanded: Boolean) {
        _expanded.value = expanded
    }

    private val _selectedText = MutableLiveData<String>()
    val selectedText : LiveData<String> = _selectedText

    fun setSelectedText(selectedText: String) {
        _selectedText.value = selectedText
    }

}