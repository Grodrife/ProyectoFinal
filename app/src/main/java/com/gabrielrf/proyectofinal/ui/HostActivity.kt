package com.gabrielrf.proyectofinal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabrielrf.proyectofinal.R

class HostActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        /*val fragment = AuthFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container_view, fragment).commit()*/
    }
}