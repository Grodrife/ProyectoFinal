package com.gabrielrf.proyectofinal.ui.main.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.databinding.TeamDetailBinding
import com.gabrielrf.proyectofinal.model.DataPlayer
import com.gabrielrf.proyectofinal.model.DataTeam
import com.gabrielrf.proyectofinal.ui.main.player.PlayerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class TeamDetailFragment : Fragment() {

    private lateinit var binding: TeamDetailBinding
    private lateinit var db: FirebaseFirestore
    private var players = mutableListOf<DataPlayer>()
    private lateinit var adapter: PlayerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = TeamDetailBinding.inflate(inflater, container, false)

        val recycler = binding.recyclerPlayer
        val layoutManager = LinearLayoutManager(requireContext())
        recycler.layoutManager = layoutManager
        adapter = PlayerAdapter(players)
        recycler.adapter = adapter

        Glide.with(binding.imageView2).load(arguments?.getString("team_logo")).into(binding.imageView2)
        binding.nombreEquipo.text = arguments?.getString("team_name")
        binding.division.text = arguments?.getString("team_division")
        binding.conferencia.text = arguments?.getString("team_conference")

        val teamId = arguments?.getInt("team_id", 0)
        db = FirebaseFirestore.getInstance()

        db.collection("teams").document(teamId.toString()).get().addOnSuccessListener { document ->
            val team = document.toObject(DataTeam::class.java)!!

            db.collection("players").whereEqualTo("team.id", team.id).get().addOnSuccessListener { documents ->
                for (it in documents){
                    players.add(it.toObject(DataPlayer::class.java))
                }
                adapter.notifyDataSetChanged()
            }.addOnFailureListener{
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }

        val botonFavoritos = binding.botonFavorito
        botonFavoritos.setOnClickListener{
            val user = FirebaseAuth.getInstance().currentUser
            val db = FirebaseFirestore.getInstance()

            val userRef = db.collection("users").document(user?.uid.toString())

            userRef.update("favorite_teams", FieldValue.arrayUnion(teamId.toString())).addOnSuccessListener {
                Toast.makeText(requireContext(), "Equipo agregado a favoritos",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(requireContext(), "Error al agregar equipo a favoritos",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}