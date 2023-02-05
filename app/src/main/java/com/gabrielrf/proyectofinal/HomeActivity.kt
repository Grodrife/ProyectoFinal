package com.gabrielrf.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.gabrielrf.proyectofinal.model.server.RemoteConnection
import com.gabrielrf.proyectofinal.model.server.RemoteService
//import com.gabrielrf.proyectofinal.ui.main.TeamAdapter
import com.gabrielrf.proyectofinal.ui.main.TeamViewModel
import com.gabrielrf.proyectofinal.ui.main.TeamViewModelFactory
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*

class HomeActivity : AppCompatActivity() {

    private val viewModel: TeamViewModel by viewModels{ TeamViewModelFactory(getString(R.string.api_key))}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teams)


    }
}