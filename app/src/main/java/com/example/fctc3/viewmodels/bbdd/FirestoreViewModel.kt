package com.example.fctc3.viewmodels.bbdd

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fct.models.Profesor
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FirestoreViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _profesores = MutableStateFlow<MutableList<Profesor>>(mutableListOf())
    val profesores = _profesores.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchProfesores()
    }

    fun fetchProfesores() {

        viewModelScope.launch {
            _isLoading.value = true
            db.collection("profesores")
                .get()
                .addOnSuccessListener { snapshot ->
                     snapshot.documents.mapNotNull { document ->
                        //_profesores.value.add

                         (
                            Profesor(
                                email = document.get("email") as String,
                                name = document.get("name") as String,
                                phoneNumber = document.get("phoneNumber") as String,
                                tutoria = document.get("tutoria") as String,
                                admin = document.get("admin") as Boolean,
                                activo = document.get("activo") as Boolean
                            )
                        )
                    }
                    _isLoading.value = false
                    //_profesores.value = fetchedProfesores as MutableList<Profesor>
                }
                .addOnFailureListener { exception ->
                    _isLoading.value = false
                    // Handle any errors appropriately
                }
        }
    }
}