package com.example.fct.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Alumno(
    var name: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var grupo: String = ""
): Parcelable