package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.Adapters.DogListAdapter
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.entities.Dog


class HomeFragment : Fragment(), OnViewItemClickedListener {
    private lateinit var dogListAdapter: DogListAdapter
    lateinit var recDogs : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recDogs = view.findViewById(R.id.rec_dogs)

        return view
    }
    override fun onStart() {
        super.onStart()
        val dogList = DogProvider.getAllDogs().toMutableList()

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

