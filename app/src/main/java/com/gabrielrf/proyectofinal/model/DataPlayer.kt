package com.gabrielrf.proyectofinal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataPlayer(
    val first_name: String,
    val height_feet: Long,
    val height_inches: Long,
    val id: Long,
    val last_name: String,
    val position: String,
    val team: DataTeam,
    val weight_pounds: Long
): Parcelable, java.io.Serializable{
    constructor(): this("",0,0,0,"","",DataTeam(),0)
}