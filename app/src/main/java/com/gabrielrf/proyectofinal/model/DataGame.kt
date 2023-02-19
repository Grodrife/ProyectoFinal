package com.gabrielrf.proyectofinal.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataGame(
    val date: String,
    val home_team: DataTeam,
    val home_team_score: Int,
    val id: Int,
    val period: Int,
    val postseason: Boolean,
    val season: Int,
    val status: String,
    val time: String,
    val visitor_team: DataTeam,
    val visitor_team_score: Int
): Parcelable, java.io.Serializable{
    constructor() : this("",DataTeam(),0,0,0,false,0,"","",DataTeam(),0)
}