package com.gabrielrf.proyectofinal.model.server

import com.gabrielrf.proyectofinal.model.Game
import com.gabrielrf.proyectofinal.model.Player
import com.gabrielrf.proyectofinal.model.Team
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RemoteService {
    @Headers("x-rapidapi-key: b0ddfe07dbmsh6daab3b699a1969p16482djsnc2b19a757cd4")
    @GET("teams")
    suspend fun getTeams(): Team

    @Headers("x-rapidapi-key: b0ddfe07dbmsh6daab3b699a1969p16482djsnc2b19a757cd4")
    @GET("players")
    suspend fun getPlayers(@Query("per_page") per_page: String): Player

    @Headers("x-rapidapi-key: b0ddfe07dbmsh6daab3b699a1969p16482djsnc2b19a757cd4")
    @GET("games")
    suspend fun getGames(@Query("seasons[]") season: Array<String>, @Query("per_page") per_page: String): Game

    @Headers("x-rapidapi-key: b0ddfe07dbmsh6daab3b699a1969p16482djsnc2b19a757cd4")
    @GET("games")
    suspend fun getGamesPerPage(@Query("page") page: Int, @Query("seasons[]") season: Array<String>): Game
}