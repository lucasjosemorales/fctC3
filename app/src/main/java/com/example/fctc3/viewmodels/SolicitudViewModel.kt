package com.example.fctc3.viewmodels

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fctc3.models.Solicitud
import java.util.LinkedHashMap

class SolicitudViewModel (private val state: SavedStateHandle) : ViewModel()
{
    private var _solicitud = mutableStateOf(Solicitud())
    var solicitud: MutableState<Solicitud> = _solicitud

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
    private val _plazas = MutableLiveData<LinkedHashMap<String, Int>>()
    val plazas : LiveData<LinkedHashMap<String, Int>> = _plazas

    fun setPlazas(plazas: LinkedHashMap<String, Int>){
        _plazas.value = plazas
    }
    fun updateConfiguracion(clave: String, valor: Int) {
        val updatedConfigurations = _solicitud.value.plazas
        updatedConfigurations[clave] = valor
        //_solicitud.value = Solicitud(plazas = updatedConfigurations)
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


}