package com.example.fc3.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlertDialogViewModel : ViewModel(){

    private val _firstInput = MutableLiveData<String>()
    val firstInput : LiveData<String> = _firstInput

    fun setFirstInput(firstInput: String) {
        _firstInput.value = firstInput
    }

    private val _secondInput = MutableLiveData<String>()
    val secondInput : LiveData<String> = _secondInput

    fun setSecondInput(secondInput: String) {
        _secondInput.value = secondInput
    }

    private val _thirdInput = MutableLiveData<String>()
    val thirdInput : LiveData<String> = _thirdInput

    fun setThirdInput(thirdInput: String) {
        _thirdInput.value = thirdInput
    }

    // Para fourthInput
    private val _fourthInput = MutableLiveData<String>()
    val fourthInput : LiveData<String> = _fourthInput

    fun setFourthInput(fourthInput: String) {
        _fourthInput.value = fourthInput
    }

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable : LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun habilitarAñadirCicloFormativo(firstInput: String, secondInput: String, thirdInput: String) {
        _loginEnable.value = campoVacio(firstInput) && campoVacio(secondInput) && campoVacio(thirdInput)
    }

    fun habilitarAñadirProfesor(firstInput: String, secondInput: String,
                                thirdInput: String, fourthInput: String)
    {
        _loginEnable.value = campoVacio(firstInput) && isValidEmail(firstInput )&& campoVacio(secondInput) && campoVacio(thirdInput)
                && campoVacio(fourthInput)
    }

    fun campoVacio(campo: String): Boolean = campo.isNotEmpty()

    fun isValidEmail(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun habilitarEliminarCiclo(firstInput: String) {
        _loginEnable.value = campoVacio(firstInput)
    }

    fun habilitarEliminarGrupo(firstInput: String) {
        _loginEnable.value = campoVacio(firstInput)
    }

    fun habilitarEliminarProfesor(firstInput: String) {
        _loginEnable.value = campoVacio(firstInput) && isValidEmail(firstInput)
    }

    //Si no se hace esto, caba vez que se abre un AlertDialog toma el ultimo valor
    fun reestablecerValores(){
        setFirstInput("")
        setSecondInput("")
        setThirdInput("")
    }
}