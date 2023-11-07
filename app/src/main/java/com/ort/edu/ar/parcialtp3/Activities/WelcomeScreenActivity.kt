package com.ort.edu.ar.parcialtp3.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceManager
import com.ort.edu.ar.parcialtp3.Fragments.FakeLoginFragment
import com.ort.edu.ar.parcialtp3.Fragments.WelcomeScreenFragment
import com.ort.edu.ar.parcialtp3.R
class WelcomeScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val isDarkThemeEnabled = sharedPref.getBoolean("switch_mode", false)
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", null)

        // Configurar el tema de acuerdo con la preferencia
        if (isDarkThemeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        if (userName != null) {
            // El usuario ya ha iniciado sesión, así que redirige a MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad actual
        } else {
            setContentView(R.layout.activity_welcome_screen)

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView3, WelcomeScreenFragment())
                    .commit()
            }
        }
    }
}