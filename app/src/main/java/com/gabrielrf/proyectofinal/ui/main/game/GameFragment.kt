package com.gabrielrf.proyectofinal.ui.main.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.model.DataGame
import com.google.firebase.firestore.FirebaseFirestore

class GameFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private var games = mutableListOf<DataGame>()
    private lateinit var adapter: GameAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_matches,container,false)

        db = FirebaseFirestore.getInstance()

        val recycler = view.findViewById<RecyclerView>(R.id.gamerecycler)
        adapter = GameAdapter(games)
        recycler.adapter = adapter

        db.collection("games").get().addOnSuccessListener { documents ->
            for (it in documents){
                games.add(it.toObject(DataGame::class.java))
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener {
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}