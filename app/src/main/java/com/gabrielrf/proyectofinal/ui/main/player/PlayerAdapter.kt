package com.gabrielrf.proyectofinal.ui.main.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielrf.proyectofinal.R
import com.gabrielrf.proyectofinal.databinding.PlayerViewBinding
import com.gabrielrf.proyectofinal.model.DataPlayer

class PlayerAdapter(private val players: MutableList<DataPlayer>): RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerAdapter.ViewHolder, position: Int) {
        val player = players[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int = players.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = PlayerViewBinding.bind(view)

        fun bind(player: DataPlayer){
            binding.nombreJugador.text = player.first_name
            binding.apellidoJugador.text = player.last_name
            binding.alturaJugador1.text = player.height_feet.toString()
            binding.alturaJugador2.text = player.height_inches.toString()
            binding.posicionJugador.text = player.position
            binding.pesoJugador.text = player.weight_pounds.toString()
        }
    }
}