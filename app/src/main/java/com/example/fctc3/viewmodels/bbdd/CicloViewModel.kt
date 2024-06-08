package com.example.fctc3.viewmodels.bbdd

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fct.models.Profesor
import com.example.fctc3.bbdd.room.AppDatabase
import com.example.fctc3.models.Ciclo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class CicloViewModel(application: Application) : AndroidViewModel(application)
{
    //Firebase
    private val dbFirebase = FirebaseFirestore.getInstance()

    //Room
    private val db = AppDatabase.getDatabase(application)
    private val dao = db.cicloDao()

    // LiveData que contiene la lista de ciclos
    private val _ciclos = MutableLiveData<List<Ciclo>>()
    val ciclos: LiveData<List<Ciclo>> = _ciclos

    init
    {
        cargarCiclos()
    }

    fun cargarCiclos()
    {
        val lista: MutableList<Ciclo> = mutableListOf()

        viewModelScope.launch {
            dbFirebase.collection("ciclos")
                .get()
                .addOnSuccessListener { snapshot ->
                    snapshot.documents.mapNotNull { document ->

                        lista.add(Ciclo(
                            nombreCorto = document.get("nombreCorto") as String,
                            nombreLargo = document.get("nombreLargo") as String,
                            familia = document.get("familia") as String
                        ))

                    }
                    _ciclos.value = lista.toList()
                }
                .addOnFailureListener { exception ->
                    // Handle any errors appropriately
                }
        }
    }

    fun añadirCiclo(ciclo: Ciclo)
    {
        viewModelScope.launch {
            val ciclo = Ciclo(ciclo.nombreCorto, ciclo.nombreLargo, ciclo.familia)
            dbFirebase.collection("ciclos").document(ciclo.nombreCorto).set(ciclo)
        }

    }

    fun eliminarCiclo(nombreCorto: String)
    {
        viewModelScope.launch {
            dbFirebase.collection("ciclos").document(nombreCorto).delete()
        }
    }

}