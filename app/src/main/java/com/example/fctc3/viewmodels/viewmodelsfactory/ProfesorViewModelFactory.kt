package com.example.fctc3.viewmodels.viewmodelsfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fctc3.viewmodels.screens.ProfesorViewModel

class ProfesorViewModelFactory(private val application: Application) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(ProfesorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfesorViewModel(application) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}