package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ort.edu.ar.parcialtp3.R

class WelcomeScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_welcome_screen, container, false)

        val btnWelcome = v.findViewById<Button>(R.id.btnWelcome)

        btnWelcome.setOnClickListener {
            // Configura la acción de navegación
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_fakeLoginFragment)
        }

        return v
    }
}
