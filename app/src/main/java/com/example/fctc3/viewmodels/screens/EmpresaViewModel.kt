package com.example.fctc3.viewmodels.screens

import android.annotation.SuppressLint
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.example.fctc3.models.Empresa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EmpresaViewModel(private val state: SavedStateHandle) : ViewModel()
{
    //Firebase
    private val dbFirebase = FirebaseFirestore.getInstance()

    private val _empresas = MutableStateFlow<List<Empresa>>(emptyList())
    val empresas = _empresas.asStateFlow().asLiveData()

    fun addEmpresa(empresa: Empresa) {
        _empresas.value = _empresas.value + empresa
    }

    fun removeEmpresaByNif(nif: String)
    {
        _empresas.value = _empresas.value.filter { empresa ->
            empresa.nif != nif
        }
    }

    init
    {
        añadirEmpresas()
    }

    //Añadir un nuevo profesor
    fun añadirEmpresa(empresa: Empresa)
    {
        //dao.insertarProfesor(profesor)
        val empresa = Empresa(
            nif = empresa.nif,
            name = empresa.name,
            localidad = empresa.localidad,
            personaContacto = empresa.personaContacto,
            tfnoContacto = empresa.tfnoContacto,
            email = empresa.email,
            repetidor = empresa.repetidor,
            contratar = empresa.contratar,
            dual = empresa.dual,
            observaciones = empresa.observaciones
        )

        dbFirebase.collection("empresas").document(empresa.nif).set(empresa)
    }

    @SuppressLint("SuspiciousIndentation")
    fun añadirEmpresas()
    {
        _empresas.value = listOf()
        dbFirebase.collection("empresas")
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.mapNotNull { document ->

                    val aux = Empresa(
                        nif = document.get("nif") as String,
                        name = document.get("name") as String,
                        localidad = document.get("localidad") as String,
                        personaContacto = document.get("personaContacto") as String,
                        tfnoContacto = document.get("tfnoContacto") as String,
                        email = document.get("email") as String,
                        repetidor = document.get("repetidor") as Boolean,
                        contratar = document.get("contratar") as Boolean,
                        dual = document.get("dual") as Boolean,
                        observaciones = document.get("observaciones") as String
                    )

                    addEmpresa(aux)
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors appropriately
            }
    }

    //Eliminar un profesor por su email
    fun eliminarEmpresa(nif: String)
    {
        dbFirebase.collection("empresas").document(nif).delete()
    }

    /* --- FIN FIREBASE --- */


    var empresa = mutableStateOf<Empresa?>(null)

    private val _nombreEmpresa = MutableLiveData<String>()
    val nombreEmpresa : LiveData<String> = _nombreEmpresa

    fun setNombreEmpresa(nombreEmpresa: String) {
        _nombreEmpresa.value = nombreEmpresa
    }

    private val _nif = MutableLiveData<String>()
    val nif : LiveData<String> = _nif

    fun setNif(nif: String) {
        _nif.value = nif
    }

    private val _localidad = MutableLiveData<String>()
    val localidad : LiveData<String> = _localidad

    fun setLocalidad(localidad: String) {
        _localidad.value = localidad
    }

    private val _personaContacto = MutableLiveData<String>()
    val personaContacto : LiveData<String> = _personaContacto

    fun setPersonaContacto(personaContacto: String) {
        _personaContacto.value = personaContacto
    }

    private val _telefonoContacto = MutableLiveData<String>()
    val telefonoContacto : LiveData<String> = _telefonoContacto

    fun setTelefonoContacto(telefonoContacto: String) {
        _telefonoContacto.value = telefonoContacto
    }

    private val _correoElectronico = MutableLiveData<String>()
    val correoElectronico : LiveData<String> = _correoElectronico

    fun setCorreoElectronico(correoElectronico: String) {
        _correoElectronico.value = correoElectronico
    }

    private val _funciones = MutableLiveData<String>()
    val funciones : LiveData<String> = _funciones

    fun setFunciones(funciones: String) {
        _funciones.value = funciones
    }

    private val _observaciones = MutableLiveData<String>()
    val observaciones : LiveData<String> = _observaciones

    fun setObservaciones(observaciones: String) {
        _observaciones.value = observaciones
    }

    private val _practicasAnt = MutableLiveData<Boolean>()
    val practicasAnt : LiveData<Boolean> = _practicasAnt

    fun setPracticasAnt(practicasAnt: Boolean) {
        _practicasAnt.value = practicasAnt
    }

    private val _contratar = MutableLiveData<Boolean>()
    val contratar : LiveData<Boolean> = _contratar

    fun setContratar(contratar: Boolean) {
        _contratar.value = contratar
    }

    private val _dual = MutableLiveData<Boolean>()
    val dual : LiveData<Boolean> = _dual

    fun setDual(dual: Boolean) {
        _dual.value = dual
    }


    /* Login */

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    fun habilitarBoton(
        nif: String,
        nombreEmpresa: String,
        localidad: String,
        personaContacto: String,
        telefonoContacto: String,
        correoElectronico: String,
        observaciones: String
    ) {
        _loginEnable.value = isValidEmail(correoElectronico) && isValidPhoneNumber(telefonoContacto) && isValidCif(nif)
                && campoVacio(nif) && campoVacio(nombreEmpresa) && campoVacio(localidad) && campoVacio(personaContacto)
                && campoVacio(telefonoContacto) && campoVacio(correoElectronico) && campoVacio(observaciones)
    }

    fun campoVacio(campo: String): Boolean = campo.isNotEmpty()

    fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPhoneNumber(phoneNumber: String): Boolean = Patterns.PHONE.matcher(phoneNumber).matches() && phoneNumber.length == 9

    fun isValidCif(nif: String): Boolean = Regex("^[A-Za-z][0-9]{8}\$").matches(nif)

    fun validarDNI(dni: String): Boolean {
        val regex = Regex("^[0-9]{8}[A-Za-z]\$")
        return regex.matches(dni)
    }

}