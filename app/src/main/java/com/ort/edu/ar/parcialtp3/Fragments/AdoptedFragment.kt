package com.ort.edu.ar.parcialtp3.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.Adapters.DogListAdapter
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.ViewModels.AdoptedViewModel
import com.ort.edu.ar.parcialtp3.databinding.FragmentAdoptedBinding
import com.ort.edu.ar.parcialtp3.entities.Dog

class AdoptedFragment : Fragment(), OnViewItemClickedListener {
    private lateinit var dogListAdapter: DogListAdapter
    lateinit var recDogs : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

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
        val dogList = DogProvider.dogList.toMutableList()

        requireActivity()
        recDogs.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        dogListAdapter = DogListAdapter(dogList, this)
        recDogs.layoutManager = linearLayoutManager
        recDogs.adapter = dogListAdapter

    }

    override fun onViewItemDetail(dog: Dog) {

    }

}