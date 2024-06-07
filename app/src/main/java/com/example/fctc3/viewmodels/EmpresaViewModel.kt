package com.example.fctc3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fctc3.models.Empresa

class EmpresaViewModel(private val state: SavedStateHandle) : ViewModel()
{
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

    /* ESTO ES DE SOLICITUD
    private val _horarioLaboral = MutableLiveData<String>()
    val horarioLaboral : LiveData<String> = _horarioLaboral

    fun setHorarioLaboral(horarioLaboral: String) {
        _horarioLaboral.value = horarioLaboral
    }*/

    private val _observaciones = MutableLiveData<String>()
    val observaciones : LiveData<String> = _observaciones

    fun setObservaciones(observaciones: String) {
        _observaciones.value = observaciones
    }

    //------------------------------------

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

}