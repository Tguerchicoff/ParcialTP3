package com.ort.edu.ar.parcialtp3.Activities

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.navigation.NavigationView
import com.ort.edu.ar.parcialtp3.Fragments.AdoptedFragment
import com.ort.edu.ar.parcialtp3.Fragments.ConfigurationFragment
import com.ort.edu.ar.parcialtp3.Fragments.DetailsFragment
import com.ort.edu.ar.parcialtp3.Fragments.FavouritesFragment
import com.ort.edu.ar.parcialtp3.Fragments.HomeFragment
import com.ort.edu.ar.parcialtp3.Fragments.ProfileFragment
import com.ort.edu.ar.parcialtp3.Fragments.PublicationFragment
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateNavHeader()
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.dog_paw)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.location)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)


        val btnLogout = findViewById<Button>(R.id.logout)
        btnLogout.setOnClickListener {
            handleLogout()
        }


        binding.bottomNavigation.background = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_home -> openFragment(HomeFragment())
                R.id.navigation_publication -> openFragment(PublicationFragment())
                R.id.navigation_adopted -> openFragment(AdoptedFragment())
                R.id.navigation_favourites -> openFragment(FavouritesFragment())
            }
            true
        }

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())
    }

    private fun updateToolbarAndNavHeader(fragment: Fragment) {
        when (fragment) {
            is HomeFragment -> supportActionBar?.title = "Inicio"
            is PublicationFragment -> supportActionBar?.title = "Publicación"
            is AdoptedFragment -> supportActionBar?.title = "Adoptados"
            is FavouritesFragment -> supportActionBar?.title = "Favoritos"
            is ProfileFragment -> supportActionBar?.title = "Perfil"
            else -> supportActionBar?.title = ""
        }
        updateNavHeader() // Actualizar el nombre en el encabezado de navegación
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            val fragment = fragmentManager.findFragmentById(R.id.fragment_container)
            updateToolbarAndNavHeader(fragment!!)
            super.onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

        updateToolbarAndNavHeader(fragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_profile -> openFragment(ProfileFragment())
            R.id.navigation_configuration -> openFragment(ConfigurationFragment())
        }

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun updateNavHeader(){
        val navigationView = findViewById<NavigationView>(R.id.navigation_drawer)
        val navHeader = navigationView.getHeaderView(0) // Asegúrate de ajustar el índice si tienes varios elementos en el header
        val textViewName = navHeader.findViewById<TextView>(R.id.UserName)
        val padding = navHeader.findViewById<ImageView>(R.id.padding)

        // Obtén el nombre del usuario de SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val userName = sharedPreferences.getString("user_name", "Usuario")
        // Configura el nombre del usuario en el TextView del nav_header
        textViewName.text = userName

        loadImageFromStorage(padding)
    }

    public fun loadImageFromStorage(imageView: ImageView) {
        // https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android

        try {
            val cw = ContextWrapper(this)
            // path to /data/data/yourapp/app_data/imageDir
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val f = File(directory, "profile.jpg")
            val b = BitmapFactory.decodeStream(FileInputStream(f))

            imageView.setImageBitmap(b)
        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
            // Si no hay imagen guardada, ponemos la imagen por defecto
            imageView.setImageResource(R.drawable.user_logged)
        }
    }

    private fun handleLogout() {
        // Redirigir a WelcomeScreenActivity y borrar el user_name de SharedPreferences
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("user_name").apply()

        // Borrar la imagen de perfil
        val cw = ContextWrapper(this)
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        val f = File(directory, "profile.jpg")
        f.delete()

        val intent = Intent(this, WelcomeScreenActivity::class.java)
        startActivity(intent)
        finish()
    }
}