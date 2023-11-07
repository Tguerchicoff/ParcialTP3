package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.Adapters.DogListAdapter
import com.ort.edu.ar.parcialtp3.Listener.OnCheckboxChangedListener
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.ViewModels.DogViewModel
import com.ort.edu.ar.parcialtp3.entities.Dog

class FavouritesFragment : Fragment(), OnViewItemClickedListener, OnCheckboxChangedListener {
    private lateinit var dogListAdapter: DogListAdapter
    lateinit var recDogs : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: DogViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_favourites, container, false)
        recDogs = view.findViewById(R.id.rec_dogs)

        return view
    }
    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(DogViewModel::class.java)
        val dogList = DogProvider.getFavoriteDogs().toMutableList()

        requireActivity()
        recDogs.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        dogListAdapter = DogListAdapter(dogList, this, this)
        recDogs.layoutManager = linearLayoutManager
        recDogs.adapter = dogListAdapter
        dogListAdapter.filterFavourite()

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
        Log.e("Viewmodel","ACa llegue!")
        if (index != -1 && viewModel.dogList.value != null && index < viewModel.dogList.value!!.size) {
            val updatedList = viewModel.dogList.value!!.toMutableList()
            updatedList[index] = dog
            viewModel.updateDogList(updatedList)
        }
    }

}