package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.Services.ActivityServiceApiBuilder
import com.ort.edu.ar.parcialtp3.databinding.FragmentWelcomeScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WelcomeScreenFragment : Fragment() {
    private var _binding: FragmentWelcomeScreenBinding? = null

    private val binding get() = _binding!!
    private val dogApiService = ActivityServiceApiBuilder.create()
    private val dogImagesList = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)

        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnWelcome = view.findViewById<Button>(R.id.btnWelcome)

        GlobalScope.launch(Dispatchers.IO){
            val breeds = arrayListOf("bulldog", "bullterrier", "spaniel");
            val subBreeds = arrayListOf("french", "staffordshire", "cocker")
            var subBreedsIndex = 0

            for (breed in breeds){
                val response = dogApiService.getThreeRandomSubBreedImages(breed, subBreeds.get(subBreedsIndex)).execute()
                subBreedsIndex+=1
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        dogImagesList.addAll(body.message)
                    }
                }

            }
            //PARA MOSTRAR LAS FOTOS DE LOS PERROS: dogImagesList[0] - dogImagesList[8]. 3 son de cada raza.
        }



        btnWelcome.setOnClickListener {
            // Realizar la transacción para mostrar FakeLoginFragment
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView3, FakeLoginFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
