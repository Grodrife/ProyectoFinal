package com.gabrielrf.proyectofinal.model

data class MetaGame(
    val current_page: Int,
    val next_page: Int,
    val per_page: Int,
    val total_count: Int,
    val total_pages: Int
)