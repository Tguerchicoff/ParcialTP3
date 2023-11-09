package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.Adapters.DogListAdapter
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.Entities.Dog
import com.ort.edu.ar.parcialtp3.Services.DogProvider

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
        val dogList = DogProvider.getAdoptedDogs().toMutableList()
        requireActivity()
        recDogs.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        dogListAdapter = DogListAdapter(dogList, this)
        recDogs.layoutManager = linearLayoutManager
        recDogs.adapter = dogListAdapter

        val textEmptyList = view?.findViewById<TextView>(R.id.textEmptyList)
        if (dogList.isEmpty()) {
            textEmptyList?.visibility = View.VISIBLE
        } else {
            textEmptyList?.visibility = View.GONE
        }
    }

    override fun onViewItemDetail(dog: Dog) {
        val detailsFragment = DetailsFragment()

        val bundle = Bundle()
        bundle.putSerializable("dog", dog)
        detailsFragment.arguments = bundle

        // Reemplazar el contenido actual del fragmento principal con el fragmento de detalles
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, detailsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}