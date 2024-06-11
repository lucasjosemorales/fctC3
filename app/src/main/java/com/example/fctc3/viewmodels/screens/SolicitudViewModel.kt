package com.example.fctc3.viewmodels.screens

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.lifecycle.*
import com.example.fctc3.models.Solicitud
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.LinkedHashMap

class SolicitudViewModel (private val state: SavedStateHandle) : ViewModel()
{
    //Firebase
    private val dbFirebase = FirebaseFirestore.getInstance()

    private val _solicitudes = MutableStateFlow<List<Solicitud>>(emptyList())
    val solicitudes = _solicitudes.asStateFlow().asLiveData()

    fun addSolicitud(solicitud: Solicitud) {
        _solicitudes.value = _solicitudes.value + solicitud
    }

    fun removeSolicitudByNif(nif: String)
    {
        _solicitudes.value = _solicitudes.value.filter { solicitud ->
            solicitud.nif != nif
        }
    }

    init
    {
        añadirSolicitudes()
    }

    //Añadir un nuevo profesor
    fun añadirSolicitud(solicitud: Solicitud)
    {
        val solicitud = Solicitud(
            nif = solicitud.nif,
            convocatoria = solicitud.convocatoria,
            curso = solicitud.curso,
            empresa = solicitud.empresa,
            funciones = solicitud.funciones,
            horario = solicitud.horario,
            plazas = solicitud.plazas,
            estado = solicitud.estado,
            coordinador = solicitud.coordinador,
            alumnos = solicitud.alumnos
        )

        dbFirebase.collection("solicitudes").document(solicitud.nif).set(solicitud)
    }

    @SuppressLint("SuspiciousIndentation")
    fun añadirSolicitudes()
    {
        _solicitudes.value = listOf()
        dbFirebase.collection("solicitudes")
            .get()
            .addOnSuccessListener { snapshot ->
                snapshot.documents.mapNotNull { document ->

                    val aux = Solicitud(
                        nif = document.get("nif") as String,
                        convocatoria = document.get("convocatoria") as String,
                        curso = document.get("curso") as String,
                        empresa = document.get("empresa") as String,
                        funciones = document.get("funciones") as String,
                        horario = document.get("horario") as String,
                        plazas = document.get("plazas") as MutableMap<String,Long>,
                        estado = document.get("estado") as String,
                        coordinador = document.get("coordinador") as String,
                        alumnos = document.get("alumnos") as MutableList<String>
                        //alumnos = document.get("alumnos")
                    )

                    addSolicitud(aux)
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors appropriately
            }
    }

    //Eliminar una solicitud por su nif
    fun eliminarSolicitud(nif: String)
    {
        dbFirebase.collection("solicitudes").document(nif).delete()
    }

    /* --- FIN FIREBASE --- */

    var solicitud = mutableStateOf<Solicitud?>(null)

    private val _nif = MutableLiveData<String>()
    val nif : LiveData<String> = _nif

    fun setNif(nif: String) {
        _nif.value = nif
    }

    private val _convocatoria = MutableLiveData<String>()
    val convocatoria : LiveData<String> = _convocatoria

    fun setConvocatoria(convocatoria: String) {
        _convocatoria.value = convocatoria
    }

    private val _curso = MutableLiveData<String>()
    val curso : LiveData<String> = _curso

    fun setCurso(curso: String) {
        _curso.value = curso
    }

    private val _nombreEmpresa = MutableLiveData<String>()
    val nombreEmpresa : LiveData<String> = _nombreEmpresa

    fun setNombreEmpresa(nombreEmpresa: String) {
        _nombreEmpresa.value = nombreEmpresa
    }

    private val _funciones = MutableLiveData<String>()
    val funciones : LiveData<String> = _funciones

    fun setFunciones(funciones: String) {
        _funciones.value = funciones
    }

    private val _horarioLaboral = MutableLiveData<String>()
    val horarioLaboral : LiveData<String> = _horarioLaboral

    fun setHorarioLaboral(horarioLaboral: String) {
        _horarioLaboral.value = horarioLaboral
    }

    //Map plazas por ciclo
    private val _plazas = MutableLiveData<LinkedHashMap<String, Long>>()
    val plazas : LiveData<LinkedHashMap<String, Long>> = _plazas

    fun setPlazas(plazas: LinkedHashMap<String, Long>){
        _plazas.value = plazas
    }
    fun updateConfiguracion(clave: String, valor: Long)
    {
        //val updatedConfigurations = solicitud.value?.plazas
        solicitud.value?.plazas?.set(clave, valor)
        //solicitud.value = Solicitud(plazas = updatedConfigurations!!)
    }

    private val _estado = MutableLiveData<String>()
    val estado : LiveData<String> = _estado

    fun setEstado(estado: String) {
        _estado.value = estado
    }

    private val _coordinador = MutableLiveData<String>()
    val coordinador : LiveData<String> = _coordinador

    fun setCoordinador(coordinador: String) {
        _coordinador.value = coordinador
    }

    //Lista de alumnos
    private val _alumnos = MutableLiveData<MutableList<String>?>()
    val alumnos : LiveData<MutableList<String>?> = _alumnos

    fun setAlumnos(alumnos: MutableList<String>?){
        _alumnos.value = alumnos
    }

    /* Login */

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    fun habilitarBoton(nif: String, nombreEmpresa: String, funciones: String, horario: String)
    {
        _loginEnable.value = isValidCif(nif) && campoVacio(nif) && campoVacio(nombreEmpresa)
                && campoVacio(funciones) && campoVacio(horario)
    }

    fun campoVacio(campo: String): Boolean = campo.isNotEmpty()

    fun isValidCif(nif: String): Boolean = Regex("^[A-Za-z][0-9]{8}\$").matches(nif)

}