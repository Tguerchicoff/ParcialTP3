package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import com.ort.edu.ar.parcialtp3.R


class ConfigurationFragment : PreferenceFragmentCompat() {
    override fun onStart() {
        super.onStart()

        val prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())

        Log.d("Preferences", prefs.getBoolean("switch_mode", false).toString())

    }
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.user_settings, rootKey)
        val themeSwitch = findPreference<SwitchPreference>("switch_mode")

        themeSwitch?.setOnPreferenceChangeListener { _, newValue ->
            val isChecked = newValue as Boolean
            if (isChecked) {
                // Cambia el tema a modo oscuro cuando se activa el SwitchPreference
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Cambiar al tema original al apagar el switchPreference
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            requireActivity().recreate() // Recrear la actividad para aplicar el nuevo tema
            true
        }
    }

}