package com.gabrielrf.proyectofinal.model

import com.google.gson.annotations.SerializedName

data class Game(
   @SerializedName("data") val game: List<DataGame>,
   @SerializedName("meta") val meta: MetaGame
)