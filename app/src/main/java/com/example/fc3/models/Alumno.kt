package com.example.fct.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alumno(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val grupo: String = ""
): Parcelable