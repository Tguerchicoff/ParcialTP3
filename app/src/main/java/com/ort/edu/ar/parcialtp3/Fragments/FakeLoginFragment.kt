package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome_screen, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}