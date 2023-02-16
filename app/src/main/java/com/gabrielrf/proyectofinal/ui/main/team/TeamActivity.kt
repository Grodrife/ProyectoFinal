package com.gabrielrf.proyectofinal.ui.main.team

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.model.DataTeam
import com.google.firebase.firestore.FirebaseFirestore

class TeamActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private var teams = mutableListOf<DataTeam>()
    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teams)

        db = FirebaseFirestore.getInstance()

        val recycler =  findViewById<RecyclerView>(R.id.recycler)
        adapter = TeamAdapter(teams)
        recycler.adapter = adapter

        db.collection("teams").get().addOnSuccessListener { documents ->
            for (it in documents){
                teams.add(it.toObject(DataTeam::class.java))
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }


    }
}

