package com.gabrielrf.proyectofinal.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.model.DataTeam
import com.gabrielrf.proyectofinal.ui.main.team.TeamAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FavTeamsFragment: Fragment() {

    private lateinit var db: FirebaseFirestore
    private var teams = mutableListOf<DataTeam>()
    private lateinit var adapter: FavTeamsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        val user = FirebaseAuth.getInstance().currentUser
        var favTeams = listOf<String>()

        db = FirebaseFirestore.getInstance()
        db.collection("users").document(user?.uid.toString()).get().addOnSuccessListener { document ->
            //val favorite_teams = document.data
            favTeams = document["favorite_teams"] as List<String>
            db.collection("teams").get().addOnSuccessListener { documents ->
                for (it in documents) {
                    if (favTeams.contains(it.id.toString())) {
                        teams.add(it.toObject(DataTeam::class.java))
                    }

                }
                val recycler = view.findViewById<RecyclerView>(R.id.recycler)
                adapter = FavTeamsAdapter(teams, parentFragmentManager)
                recycler.adapter = adapter
                adapter.notifyDataSetChanged()
            }.addOnFailureListener{
                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}