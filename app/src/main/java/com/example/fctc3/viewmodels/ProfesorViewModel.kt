package com.example.fctc3.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.fctc3.bbdd.room.AppDatabase
import com.example.fct.models.Profesor
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ProfesorViewModel(application: Application) : AndroidViewModel(application)
{
    //Firebase
    private val dbFirebase = FirebaseFirestore.getInstance()

    //Room
    private val db = AppDatabase.getDatabase(application)
    private val dao = db.profesorDao()

    // LiveData que contiene la lista de profesores
    private val _profesores = MutableLiveData<List<Profesor>>()
    val profesores: LiveData<List<Profesor>> = _profesores

    init
    {
        añadirProfesores()
        cargarProfesores()
    }

    // Carga los profesores desde la base de datos
    fun cargarProfesores() {
        viewModelScope.launch {
            _profesores.value = dao.obtenerTodosLosProfesores()
        }
    }

    //Añadir un nuevo profesor
    fun añadirProfesor(profesor: Profesor)
    {
        viewModelScope.launch {
            dao.insertarProfesor(profesor)
        }
    }

    fun añadirProfesores()
    {
        val lista: MutableList<Profesor> = mutableListOf()

        viewModelScope.launch {

            dbFirebase.collection("profesores")
                .get()
                .addOnSuccessListener { snapshot ->
                    snapshot.documents.mapNotNull { document ->

                        lista.add(Profesor(
                                    email = document.get("email") as String,
                                    name = document.get("name") as String,
                                    phoneNumber = document.get("phoneNumber") as String,
                                    tutoria = document.get("tutoria") as String,
                                    admin = document.get("admin") as Boolean,
                                    activo = document.get("activo") as Boolean
                                ))

                    }
                    _profesores.value = lista.toList()
                }
                .addOnFailureListener { exception ->
                    // Handle any errors appropriately
                }
        }

    }

    //Eliminar todos
    fun eliminarTodos()
    {
        viewModelScope.launch {
            dao.eliminarTodos()
        }
    }

    //Eliminar un profesor por su email
    fun eliminarProfesor(email: String)
    {
        viewModelScope.launch {
            dao.eliminarProfesor(email)
        }
    }

    /*-------------- FIN ROOM Y FIREBASE ----------------------------------------*/

    var profesor = mutableStateOf<Profesor>(Profesor())

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