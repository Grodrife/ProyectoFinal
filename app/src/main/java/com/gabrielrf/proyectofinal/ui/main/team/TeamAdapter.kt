package com.gabrielrf.proyectofinal.ui.main.team

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.databinding.TeamViewBinding
import com.gabrielrf.proyectofinal.model.DataPlayer
import com.gabrielrf.proyectofinal.model.DataTeam
import com.gabrielrf.proyectofinal.ui.main.player.PlayerAdapter
import com.google.firebase.firestore.FirebaseFirestore

class TeamAdapter(private val teams: MutableList<DataTeam>): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.team_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = teams[position]
        holder.bind(team)
    }

    override fun getItemCount(): Int = teams.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = TeamViewBinding.bind(view)

        fun bind(team: DataTeam){
            binding.textView2.text = team.name
            Glide.with(binding.imageView3).load("https://assets.stickpng.com/images/58428defa6515b1e0ad75ab4.png").into(binding.imageView3)

            itemView.setOnClickListener {
                val intentTeam = Intent(context, TeamDetailActivity::class.java)
                intentTeam.putExtra("team_name", team.full_name)
                intentTeam.putExtra("team_division", team.division)
                intentTeam.putExtra("team_conference", team.conference)
                intentTeam.putExtra("team_id", team.id)
                //intentTeam.putExtra("team", team)
                context.startActivity(intentTeam)
            }
        }
    }
}
