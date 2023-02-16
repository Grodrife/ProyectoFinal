package com.gabrielrf.proyectofinal.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gabrielrf.proyectofinal.databinding.TeamDetailBinding

class TeamDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = TeamDetailBinding.inflate(inflater, container, false)
        val team = arguments?.getSerializable("team") as? Map<String,String>
        binding.nombreEquipo.text = team?.get("name")
        binding.division.text = team?.get("division")
        binding.conferencia.text = team?.get("conference")
        return binding.root
    }
}