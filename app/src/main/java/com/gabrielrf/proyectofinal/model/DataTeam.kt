package com.gabrielrf.proyectofinal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTeam(
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val full_name: String,
    val id: Int,
    val name: String,
    val logo: String
): Parcelable, java.io.Serializable{
    constructor() : this("","","","","",0,"","")
}