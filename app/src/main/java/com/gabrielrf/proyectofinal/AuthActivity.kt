package com.gabrielrf.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gabrielrf.proyectofinal.model.server.RemoteConnection
import com.gabrielrf.proyectofinal.ui.main.team.TeamActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*

class AuthActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val loginButton = findViewById<Button>(R.id.botonAceptar)
        val createButton = findViewById<Button>(R.id.botonNuevaCuenta)
        val errorTextView = findViewById<TextView>(R.id.textError)

         /*GlobalScope.launch(Dispatchers.Main) {
             async(Dispatchers.IO){
                 loadDataTeam()
                 loadDataPlayers()
                 loadDataGames()
             }
         }*/

        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.usuario).text.toString()
            val password = findViewById<EditText>(R.id.contrasenya).text.toString()
            errorTextView.visibility = View.GONE
            if (username.isEmpty()) {
                errorTextView.text = "Usuario está vacío"
                errorTextView.visibility = View.VISIBLE
            } else if (password.isEmpty()) {
                errorTextView.text = "Contraseña está vacía"
                errorTextView.visibility = View.VISIBLE
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, TeamActivity::class.java)
                            startActivity(intent)
                        } else {
                            errorTextView.text = "Usuario o contraseña incorrecto"
                            errorTextView.visibility = View.VISIBLE
                        }
                    }

            }
        }
        createButton.setOnClickListener {
            val newUsername = findViewById<EditText>(R.id.usuario).text.toString()
            val newPassword = findViewById<EditText>(R.id.contrasenya).text.toString()
            errorTextView.visibility = View.GONE
            if (newUsername.isEmpty()) {
                errorTextView.text = "Usuario está vacío"
                errorTextView.visibility = View.VISIBLE
            } else if (newPassword.isEmpty()) {
                errorTextView.text = "Contraseña está vacía"
                errorTextView.visibility = View.VISIBLE
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(newUsername, newPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            usuario()
                            val intent = Intent(this, TeamActivity::class.java)
                            startActivity(intent)
                        } else {
                            errorTextView.text = "Este correo ya está vinculado"
                            errorTextView.visibility = View.VISIBLE
                        }
                    }
            }


        }
    }

    private fun usuario() {
        val user = FirebaseAuth.getInstance().currentUser
        db.collection("users").document(user?.uid.toString())
            .set(
                hashMapOf(
                    "favorite_teams" to listOf<String>()
                )
            )
    }

    suspend fun loadDataTeam() {
            val datosEquipos = RemoteConnection.service.getTeams()
            for (it in datosEquipos.team) {
                db.collection("teams").document(it.id.toString())
                    .set(
                        hashMapOf(
                            "abbreviation" to it.abbreviation,
                            "city" to it.city,
                            "conference" to it.conference,
                            "division" to it.division,
                            "full_name" to it.full_name,
                            "id" to it.id,
                            "name" to it.name
                        )
                    )
            }
    }

    suspend fun loadDataPlayers() {
        val datosJugadores = RemoteConnection.service.getPlayers(100)
        for (it in datosJugadores.player) {
            if (!it.position.isNullOrBlank()){
                db.collection("players").document(it.id.toString())
                    .set(
                        hashMapOf(
                            "first_name" to it.first_name,
                            "height_feet" to it.height_feet,
                            "height_inches" to it.height_inches,
                            "id" to it.id,
                            "last_name" to it.last_name,
                            "position" to it.position,
                            "team" to it.team,
                            "weight_pounds" to it.weight_pounds
                        )
                    )
            }
        }
    }

    suspend fun loadDataGames() {
        val datosPartidos = RemoteConnection.service.getGames(arrayOf("2022"),100)
        for (it in datosPartidos.game) {
            db.collection("games").document(it.id.toString())
                .set(
                    hashMapOf(
                        "date" to it.date,
                        "home_team" to it.home_team,
                        "home_team_score" to it.home_team_score,
                        "id" to it.id,
                        "season" to it.season,
                        "visitor_team" to it.visitor_team,
                        "visitor_team_score" to it.visitor_team_score
                    )
                )
        }
    }
}