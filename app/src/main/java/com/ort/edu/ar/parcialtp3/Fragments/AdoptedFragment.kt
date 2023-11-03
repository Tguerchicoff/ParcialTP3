package com.ort.edu.ar.parcialtp3.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.ViewModels.AdoptedViewModel
import com.ort.edu.ar.parcialtp3.databinding.FragmentAdoptedBinding

class AdoptedFragment : Fragment() {

    private var _binding: FragmentAdoptedBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val adoptedViewModel =
            ViewModelProvider(this).get(AdoptedViewModel::class.java)

        _binding = FragmentAdoptedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textAdopted
        adoptedViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}