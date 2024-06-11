package com.example.fc3.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _passwordVisibility = MutableLiveData<Boolean>()
    val passwordVisibility : LiveData<Boolean> = _passwordVisibility

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    //--------------------------------------------------------------------------------
    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setPasswordVisibility(passwordVisibility: Boolean) {
        _passwordVisibility.value = !passwordVisibility
    }

    //Botón de login de LoginScreen
    fun habilitarBoton(email: String, password: String) {
        _loginEnable.value = isValidEmail(email) && campoVacio(email) && campoVacio(password)
    }

    //Botón de recuperar contraseña en ForgotSCreen
    fun habilitarBoton(email: String) {
        _loginEnable.value = isValidEmail(email) && campoVacio(email)
    }

    fun campoVacio(campo: String): Boolean = campo.isNotEmpty()

    fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}