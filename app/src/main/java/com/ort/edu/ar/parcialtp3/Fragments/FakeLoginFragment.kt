package com.ort.edu.ar.parcialtp3.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ort.edu.ar.parcialtp3.Activities.MainActivity
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.ViewModels.HomeViewModel
import com.ort.edu.ar.parcialtp3.databinding.FragmentFakeLoginBinding
import com.ort.edu.ar.parcialtp3.databinding.FragmentHomeBinding


class FakeLoginFragment : Fragment() {
    private var _binding: FragmentFakeLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFakeLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val editTextName = binding.editTextText
        val btnWelcome = binding.btnWelcome

        btnWelcome.setOnClickListener {
            val name = editTextName.text.toString().trim()

            if (name.isNotEmpty()) {
                // Guardar el nombre en SharedPreferences
                val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("user_name", name)
                editor.apply()

                // Redirigir a MainActivity
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                // Mostrar un mensaje de error si el nombre está vacío
                Toast.makeText(requireContext(), "Por favor, ingrese un nombre", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}