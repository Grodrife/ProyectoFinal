package com.gabrielrf.proyectofinal.model

data class DataPlayer(
    val first_name: String,
    val height_feet: Int,
    val height_inches: Int,
    val id: Int,
    val last_name: String,
    val position: String,
    val team: DataTeam,
    val weight_pounds: Int
)