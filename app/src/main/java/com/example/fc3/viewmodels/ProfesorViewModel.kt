package com.example.fc3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fct.models.Profesor

class ProfesorViewModel(private val state: SavedStateHandle) : ViewModel()
{

    var profesor = mutableStateOf<Profesor?>(null)

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