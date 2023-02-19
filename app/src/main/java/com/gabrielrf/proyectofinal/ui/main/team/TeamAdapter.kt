package com.gabrielrf.proyectofinal.ui.main.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.databinding.TeamViewBinding
import com.gabrielrf.proyectofinal.model.DataTeam
import com.google.firebase.firestore.FirebaseFirestore

class TeamAdapter(private val teams: MutableList<DataTeam>, private val fragmentManager: FragmentManager): RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

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
            var logo = ""
                FirebaseFirestore.getInstance().collection("logos").document(team.id.toString()).get().addOnSuccessListener { document ->
                    logo = document.data?.get("logo") as String
                    Glide.with(binding.imageView3).load(logo).into(binding.imageView3)
                }




            itemView.setOnClickListener {
                val fragment = TeamDetailFragment()
                val bundle = Bundle()
                bundle.putString("team_name", team.full_name)
                bundle.putString("team_division", team.division)
                bundle.putString("team_conference", team.conference)
                bundle.putInt("team_id", team.id)
                bundle.putString("team_logo", logo)

                //bundle.putParcelable("team", team)
                fragment.arguments = bundle

                fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_main, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}
