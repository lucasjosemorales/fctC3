package com.example.fc3.viewmodels

import android.util.Patterns
import androidx.lifecycle.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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

    /* COMPROBAR QUE EL USUARIO ES ADMIN */

    private val _isAdmin = MutableStateFlow<Boolean?>(null)
    val isAdmin = _isAdmin.asStateFlow().asLiveData()
    val db = Firebase.firestore

    fun checkIfUserIsAdmin(email: String) {
        viewModelScope.launch {
            val docRef = db.collection("profesores").document(email)
            val doc = docRef.get().await()  // Use await from kotlinx-coroutines-play-services
            _isAdmin.value = doc.getBoolean("admin") ?: false
        }
    }



}