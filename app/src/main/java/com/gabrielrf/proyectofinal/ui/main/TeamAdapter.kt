package com.gabrielrf.proyectofinal.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.databinding.TeamViewBinding
import com.gabrielrf.proyectofinal.model.DataTeam

class TeamAdapter(val teams: List<DataTeam>): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*holder.bind(teams[position])
        holder.itemView.setOnClickListener {
            listener(teams[position])
        }*/
    }

    override fun getItemCount(): Int = teams.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = TeamViewBinding.bind(view)

        fun bind(team: DataTeam){
            binding.imageView3.setImageURI("https://cdn.freebiesupply.com/images/large/2x/nba-logo-transparent.png".toUri())
            binding.textView2.text = team.full_name
        }
    }
}
