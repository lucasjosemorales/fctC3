package com.example.fc3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fct.models.Alumno

class AlumnoViewModel(private val state: SavedStateHandle) : ViewModel() {

    var alumno = mutableStateOf<Alumno?>(null)
}