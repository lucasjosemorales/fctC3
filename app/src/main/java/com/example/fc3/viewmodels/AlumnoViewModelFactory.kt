package com.example.fc3.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry

class AlumnoViewModelFactory(private val entry: NavBackStackEntry) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(AlumnoViewModel::class.java))
        {
            return AlumnoViewModel(entry.savedStateHandle) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}