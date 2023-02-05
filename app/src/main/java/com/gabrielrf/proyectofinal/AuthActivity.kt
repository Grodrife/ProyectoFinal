package com.gabrielrf.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gabrielrf.proyectofinal.model.server.RemoteConnection
import com.gabrielrf.proyectofinal.ui.main.TeamActivity
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

       /* GlobalScope.launch(Dispatchers.Main) {
            val datos = async(Dispatchers.IO){
                loadDataTeam()
                loadDataPlayers()
                loadDataGames()
            }
        }*/

        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.usuario).text.toString()
            val password = findViewById<EditText>(R.id.contrasenya).text.toString()

            if (username.isEmpty()) {
                // Mostrar un mensaje de error para el usuario
                Toast.makeText(this, "El nombre de usuario está vacío", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                // Mostrar un mensaje de error para el usuario
                Toast.makeText(this, "La contraseña está vacía", Toast.LENGTH_SHORT).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, TeamActivity::class.java)
                            startActivity(intent)
                        } else {
                            errorTextView.visibility = View.VISIBLE
                        }
                    }

            }
        }
            createButton.setOnClickListener {
                val newUsername = findViewById<EditText>(R.id.usuario).text.toString()
                val newPassword = findViewById<EditText>(R.id.contrasenya).text.toString()
                if (newUsername.isEmpty()) {
                    // Mostrar un mensaje de error para el usuario
                    Toast.makeText(this, "El nombre de usuario está vacío", Toast.LENGTH_SHORT)
                        .show()
                } else if (newPassword.isEmpty()) {
                    // Mostrar un mensaje de error para el usuario
                    Toast.makeText(this, "La contraseña está vacía", Toast.LENGTH_SHORT).show()
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(newUsername, newPassword)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, TeamActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Este correo ya tiene cuenta", Toast.LENGTH_SHORT)
                            }
                        }
                }


            }
    }

    suspend fun loadDataTeam(){
        withContext(Dispatchers.IO){
            val datosEquipos = RemoteConnection.service.getTeams()
            for (it in datosEquipos.team){
                db.collection("teams").document(it.id.toString())
                    .set(hashMapOf("abbreviation" to it.abbreviation,
                        "city" to it.city,
                        "conference" to it.conference,
                        "division" to it.division,
                        "full_name" to it.full_name,
                        "name" to it.name))
            }
        }
    }

    suspend fun loadDataPlayers(){
        val datosJugadores = RemoteConnection.service.getPlayers()
        for (it in datosJugadores.player){
            db.collection("players").document(it.id.toString())
                .set(hashMapOf("first_name" to it.first_name,
                    "height_feet" to it.height_feet,
                    "height_inches" to it.height_inches,
                    "last_name" to it.last_name,
                    "position" to it.position,
                    "team_id" to it.team.id,
                    "weight_pounds" to it.weight_pounds))
        }
    }

    suspend fun loadDataGames(){
        val datosPartidos = RemoteConnection.service.getGames(arrayOf("2022"))
        for (it in datosPartidos.game){
            db.collection("games").document(it.id.toString())
                .set(hashMapOf("date" to it.date,
                    "home_team" to it.home_team.id,
                    "home_team_score" to it.home_team_score,
                    "season" to it.season,
                    "visitor_team" to it.visitor_team.id,
                    "visitor_team_score" to it.visitor_team_score))
        }

        /*for (i in 1..datosPartidos.meta.total_pages){
            val consulta = RemoteConnection.service.getGamesPerPage(i, arrayOf("2022"))
            for (it in consulta.game){
                db.collection("games").document(it.id.toString())
                    .set(hashMapOf("date" to it.date,
                        "home_team" to it.home_team.id,
                        "home_team_score" to it.home_team_score,
                        "season" to it.season,
                        "visitor_team" to it.visitor_team.id,
                        "visitor_team_score" to it.visitor_team_score))
            }
        }*/
    }
}