package com.gabrielrf.proyectofinal.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.databinding.TeamsBinding
import com.gabrielrf.proyectofinal.model.DataTeam
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TeamActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teams)
        val recycler =  findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter = TeamAdapter(teams)

    }
}

private val teams = (1..100).map {
    DataTeam(
        "Abbr",
        "City",
        "Conference",
        "Division",
        "FName",
        it,
        "Name"
    )
}