package com.gabrielrf.proyectofinal.model

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("data") val player: List<DataPlayer>,
    @SerializedName("meta") val meta: MetaPlayer
)