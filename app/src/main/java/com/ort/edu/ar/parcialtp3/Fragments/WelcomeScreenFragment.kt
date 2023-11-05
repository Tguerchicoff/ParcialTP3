package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ort.edu.ar.parcialtp3.Model.RandomImagesAPIResponse
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.Services.ActivityServiceApiBuilder
import com.ort.edu.ar.parcialtp3.databinding.FragmentWelcomeScreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WelcomeScreenFragment : Fragment() {
    private var _binding: FragmentWelcomeScreenBinding? = null

    private val binding get() = _binding!!

    private val dogApiService = ActivityServiceApiBuilder.create()
    private var imageUrls: MutableList<String> = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val razasDePerros = arrayListOf("Borzoi", "Cockapoo", "Rottweiler")

        GlobalScope.launch(Dispatchers.IO) {
            //for (raza in razasDePerros) {
                try {
                    val response = dogApiService.getThreeRandomBreedImages("Borzoi").execute()

                    if (response.isSuccessful) {
                        val randomImagesResponse = response.body()
                        if (randomImagesResponse != null) {
                            val imageUrlsList = randomImagesResponse.message
                            imageUrls.addAll(imageUrlsList)
                        }
                    }
                } catch (e: Exception) {
                    // Maneja el error de la llamada a la API aquí
                    e.printStackTrace()
                }
            }
        //}
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnWelcome = view.findViewById<Button>(R.id.btnWelcome)
        val imageTest = view.findViewById<ImageView>(R.id.imageTest)

        btnWelcome.setOnClickListener {
            // Realizar la transacción para mostrar FakeLoginFragment
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView3, FakeLoginFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }

        // Verifica si se han obtenido suficientes imágenes antes de mostrarlas
            // Muestra las imágenes en la vista
        if(imageUrls.isEmpty()){
            Log.e("ImageURLS", "ESTOY VACIO!!!")
        }else{
            val imageUrl = imageUrls[0]
            Picasso.get()
                .load(imageUrl)
                .into(imageTest)
        }


    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }

