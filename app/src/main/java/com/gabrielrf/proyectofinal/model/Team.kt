package com.gabrielrf.proyectofinal.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("data") val team: List<DataTeam>,
    @SerializedName("meta") val meta: MetaTeam
)