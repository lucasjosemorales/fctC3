package com.example.fc3.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fc3.models.Solicitud
import java.util.LinkedHashMap

class SolicitudViewModel (private val state: SavedStateHandle) : ViewModel()
{

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

    private val _estado = MutableLiveData<String>()
    val estado : LiveData<String> = _estado

    fun setEstado(estado: String) {
        _estado.value = estado
    }

    private val _correoElectronico = MutableLiveData<String>()
    val correoElectronico : LiveData<String> = _correoElectronico

    fun setCorreoElectronico(correoElectronico: String) {
        _correoElectronico.value = correoElectronico
    }

    //Mapa plazas por ciclo
    private val _plazas = MutableLiveData<LinkedHashMap<String, Int>>()
    val plazas : LiveData<LinkedHashMap<String, Int>> = _plazas

    fun setPlazas(plazas: LinkedHashMap<String, Int>){
        _plazas.value = plazas
    }

    //Lista de alumnos
    private val _alumnos = MutableLiveData<MutableList<String>?>()
    val alumnos : LiveData<MutableList<String>?> = _alumnos

    fun setAlumnos(alumnos: MutableList<String>?){
        _alumnos.value = alumnos
    }


}