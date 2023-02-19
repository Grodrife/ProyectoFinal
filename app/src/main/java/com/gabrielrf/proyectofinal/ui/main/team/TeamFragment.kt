package com.gabrielrf.proyectofinal.ui.main.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.model.DataTeam
import com.google.firebase.firestore.FirebaseFirestore

class TeamFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private var teams = mutableListOf<DataTeam>()
    private lateinit var adapter: TeamAdapter

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_teams, container, false)

        db = FirebaseFirestore.getInstance()



        db.collection("teams").get().addOnSuccessListener { documents ->
            for (it in documents){
                teams.add(it.toObject(DataTeam::class.java))
            }
            val recycler = view.findViewById<RecyclerView>(R.id.recycler)
            adapter = TeamAdapter(teams, parentFragmentManager)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }.addOnFailureListener{
            Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}

