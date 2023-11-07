package com.ort.edu.ar.parcialtp3.Adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.Fragments.DogProvider.Companion.dogList
import com.ort.edu.ar.parcialtp3.Holder.DogHolder
import com.ort.edu.ar.parcialtp3.Listener.OnCheckboxChangedListener
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.entities.Dog
import com.ort.edu.ar.parcialtp3.R

class DogListAdapter(private val dogsList: MutableList<Dog> = mutableListOf(), private val onItemClick: OnViewItemClickedListener, private var onCheckBoxClick: OnCheckboxChangedListener) : RecyclerView.Adapter<DogHolder>() {
    // No se va a mutar la lista original
    private val filteredList: MutableList<Dog> = mutableListOf()

    override fun getItemCount() = dogsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_info, parent, false)
        return DogHolder(view)
    }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {
        val dog = dogsList[position]
        holder.render(dog)
        holder.getCardLayout().setOnClickListener {
            onItemClick.onViewItemDetail(dog)
        }
        holder.favoriteCheckBox?.setOnClickListener {
            onCheckBoxClick.onCheckboxChanged(dog)
        }
    }

    fun filterHome() {
        val filteredAdoptedList = mutableListOf<Dog>()
        for (item in dogsList) {
            if (!item.isAdopted) {
                filteredAdoptedList.add(item)
            }
        }
        updateDogList(filteredAdoptedList)
    }

    fun filterAdopted() {
        val filteredAdoptedList = mutableListOf<Dog>()
        for (item in dogsList) {
            if (item.isAdopted) {
                filteredAdoptedList.add(item)
            }
        }
        updateDogList(filteredAdoptedList)
    }

    fun filterFavourite() {
        val filteredFavouriteList = mutableListOf<Dog>()
        for (item in dogsList) {
            if (item.isFavorite) {
                filteredFavouriteList.add(item)
            }
        }
        updateDogList(filteredFavouriteList)
    }

    fun getDogIndex(dog: Dog): Int {
        return dogsList.indexOf(dog)
    }

    fun updateDogList(newDogList: List<Dog>) {
        dogsList.clear()
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }
}