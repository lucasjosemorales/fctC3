package com.example.fctc3.bbdd

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.fctc3.screens.bottom_screens.ProfesorItem
import com.example.fct.models.Alumno
import com.example.fct.models.Profesor
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

sealed class AuthRes<out T> {
    data class Success<T>(val data: T) : AuthRes<T>()
    data class Error(val errorMessage: String) : AuthRes<Nothing>()
}

class Firebase (private val context: Context){


    //----------------------------AUTHENTICATION----------------------------------------------------

    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private val signInClient = Identity.getSignInClient(context)

    //INICIO DE SESION CON EMAIL-CONTRASEÑA
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            AuthRes.Success(authResult.user)
        } catch (e: Exception) {
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    //REESTABLECER CONTRASEÑA
    suspend fun resetPassword(email: String): AuthRes<Unit> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            AuthRes.Success(Unit)
        } catch (e: Exception) {
            AuthRes.Error(e.message ?: "Error al restablecer la contraseña")
        }
    }

    //CREAR USUARIO USANDO EMAIL-CONTRASEÑA
    suspend fun createUserWithEmailAndPassword(email: String, password: String): AuthRes<FirebaseUser?> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            AuthRes.Success(authResult.user)
        } catch(e: Exception) {
            AuthRes.Error(e.message ?: "Error al crear el usuario")
        }
    }

    //CERRAR SESION DE FIREBASE Y DE LA CUENTA DE GOOGLE
    fun signOut() {
        auth.signOut()
        //signInClient.signOut()

    }

    //OBTENER USUARIO CON SESIÓN ABIERTA
    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    //-----------------------------FIRESTORE-------------------------------------------------------

    val db = Firebase.firestore

    //CARGAR PROFESORES DE FIRESCTORE
    fun getProfesores(): List<Profesor> {
        var profesores = mutableListOf<Profesor>()

        db.collection("profesores")
            .get()
            .addOnSuccessListener { result ->
                Toast.makeText(context, "Total profesores: ${result.documents.size}", Toast.LENGTH_LONG).show()

                for (document in result) {
                    val profe = document.toObject(Profesor::class.java)
                    profe?.email = document.id
                    /*var profesor =
                        Profesor(
                            document.get("name").toString(),
                            document.get("email").toString(),
                            document.get("phoneNumber").toString(),
                            document.get("tutoria").toString(),
                            document.getBoolean("admin")!!,
                            document.getBoolean("activo")!!
                        )
                    profesores.add(profesor)*/
                    profe?.let { profesores.add(it) }
                    //profesores.add(profe)
                    Toast.makeText(context, "Profesores cargados correctamente", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error getting documents: ${exception}", Toast.LENGTH_LONG).show()
            }
        return profesores
    }

    //------------------------FUNCIONES PROFESORES FIREBASE-------------------------------------------
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun esAdmin(email: String?): Boolean
    {
        val docRef = firestore.collection("profesores").document(email!!)
        var admin:Boolean = false

        docRef
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    admin = document.getBoolean("admin") as Boolean
                    Toast.makeText(context, "Admin1: ${admin}", Toast.LENGTH_LONG).show()
                } else {
                    // El documento no fue encontrado
                }
            }.await()

        Toast.makeText(context, "Admin2: ${admin}", Toast.LENGTH_LONG).show()
        return admin
    }

    fun getProfesoresFlow(): Flow<List<Profesor>> = callbackFlow {

        val profeRef = firestore.collection("profesores")

        val subscription = profeRef.addSnapshotListener { snapshot, _ ->
            snapshot?.let { querySnapshot ->
                //Toast.makeText(context, "Total profesores: ${snapshot.documents.size}", Toast.LENGTH_LONG).show()

                val profes = mutableListOf<Profesor>()
                for (document in querySnapshot.documents) {
                    Toast.makeText(context, "Total profesores: ${querySnapshot.documents.size}", Toast.LENGTH_LONG).show()
                    var profe = document.toObject(Profesor::class.java)
                    profe?.email = document.id
                    profe?.let{ profes.add(it)}
                }
                trySend(profes).isSuccess
            }
        }
        awaitClose { subscription.remove() }
    }

    suspend fun addProfesor(profesor: Profesor) {
        firestore.collection("profesores").add(profesor).await()
    }

    suspend fun updateProfesor(profesor: Profesor) {
        val profeRef = profesor.email?.let { firestore.collection("profesores").document(profesor.email) }
        profeRef?.set(profesor)?.await()
    }

    suspend fun deleteProfesor(email: String) {
        val profeRef = firestore.collection("profesores").document(email)
        profeRef.delete().await()
    }


    //-----------------FUNCIONES ALUMNOS FIREBASE------------------------------------------------------
    fun getAlumnos(): List<Alumno> {
        var alumnos = mutableListOf<Alumno>()

        db.collection("alumnos")
            .get()
            .addOnSuccessListener { result ->
                Toast.makeText(context, "Total alumnos: ${result.documents.size}", Toast.LENGTH_LONG).show()
                for (document in result) {
                    val alum = document.toObject(Alumno::class.java)
                    alum?.email = document.id
                    alum?.let { alumnos.add(it) }
                    Toast.makeText(context, "Profesores cargados correctamente", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Error getting documents: ${exception}", Toast.LENGTH_LONG).show()
            }
        return alumnos
    }

}