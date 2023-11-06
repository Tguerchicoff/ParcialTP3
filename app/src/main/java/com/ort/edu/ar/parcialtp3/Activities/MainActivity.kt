package com.ort.edu.ar.parcialtp3.Activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)


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

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.getOnBackPressedDispatcher().onBackPressed()
        }
    }

    private fun openFragment(fragment: Fragment){
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_profile -> openFragment(ProfileFragment())
            R.id.navigation_configuration -> openFragment(ConfigurationFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

}