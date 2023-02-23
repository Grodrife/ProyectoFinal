package com.gabrielrf.proyectofinal.ui.main.game

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.databinding.MatchViewBinding
import com.gabrielrf.proyectofinal.model.DataGame
import com.gabrielrf.proyectofinal.model.DataTeam
import com.google.firebase.firestore.FirebaseFirestore

class GameAdapter(private val games: MutableList<DataGame>): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.match_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
    }

    override fun getItemCount(): Int = games.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = MatchViewBinding.bind(view)

        fun bind(game: DataGame){
            var logo1 = ""
            var logo2 = ""
            FirebaseFirestore.getInstance().collection("logos").document(game.home_team.id.toString()).get().addOnSuccessListener { document ->
                logo1 = document.data?.get("logo") as String
            }
            FirebaseFirestore.getInstance().collection("logos").document(game.visitor_team.id.toString()).get().addOnSuccessListener { document ->
                logo2 = document.data?.get("logo") as String
            }
            binding.equipo1.text = game.home_team.full_name
            Glide.with(binding.logo1).load(logo1).into(binding.logo1)
            binding.fechaPartido.text = game.date
            binding.puntos1.text = game.home_team_score.toString()
            binding.puntos2.text = game.visitor_team_score.toString()
            Glide.with(binding.logo2).load(logo2).into(binding.logo2)
            binding.equipo2.text = game.visitor_team.full_name
        }
    }



}