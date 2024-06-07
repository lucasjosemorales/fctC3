package com.example.fctc3.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel()
{
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(email: String, password: String, onComplete: (Boolean) -> Unit)
    {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            onComplete(task.isSuccessful)
        }
    }

    fun signIn(email: String, password: String, onComplete: (Boolean) -> Unit)
    {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            onComplete(task.isSuccessful)
        }
    }
}