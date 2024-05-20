package com.example.fc3.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.fct.models.Profesor

class ProfesorViewModel(private val state: SavedStateHandle) : ViewModel() {

    var profesor = mutableStateOf<Profesor?>(null)
}