package com.ort.edu.ar.parcialtp3.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.Adapters.DogListAdapter
import com.ort.edu.ar.parcialtp3.Listener.OnCheckboxChangedListener
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.ViewModels.AdoptedViewModel
import com.ort.edu.ar.parcialtp3.ViewModels.DogViewModel
import com.ort.edu.ar.parcialtp3.databinding.FragmentAdoptedBinding
import com.ort.edu.ar.parcialtp3.entities.Dog

class AdoptedFragment : Fragment(), OnViewItemClickedListener, OnCheckboxChangedListener {
    private lateinit var dogListAdapter: DogListAdapter
    lateinit var recDogs : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_adopted, container, false)
        recDogs = view.findViewById(R.id.rec_dogs)

        return view
    }
    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(DogViewModel::class.java)
        val dogList = DogProvider.getAdoptedDogs().toMutableList()
        requireActivity()
        recDogs.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        dogListAdapter = DogListAdapter(dogList, this, this)
        recDogs.layoutManager = linearLayoutManager
        recDogs.adapter = dogListAdapter
        dogListAdapter.filterAdopted()

        viewModel.dogList.observe(viewLifecycleOwner, Observer { updatedList ->
            // Actualiza la lista de perros en el adaptador
            dogListAdapter.updateDogList(updatedList)
        })

    }

    override fun onViewItemDetail(dog: Dog) {

    }


    override fun onCheckboxChanged(dog: Dog) {
        // Obtiene el índice del perro
        val index = dogListAdapter.getDogIndex(dog)
        if (index != -1) {
            // Actualiza la lista en el ViewModel cuando cambia el CheckBox
            val updatedList = viewModel.dogList.value?.toMutableList() ?: mutableListOf()
            updatedList[index] = dog
            viewModel.updateDogList(updatedList)
        }
    }

}