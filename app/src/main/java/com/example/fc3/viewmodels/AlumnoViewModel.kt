package com.example.fc3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fct.models.Alumno

class AlumnoViewModel(private val state: SavedStateHandle) : ViewModel()
{
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