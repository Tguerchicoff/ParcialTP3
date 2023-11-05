package com.ort.edu.ar.parcialtp3.Adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.Fragments.DogProvider.Companion.dogList
import com.ort.edu.ar.parcialtp3.Holder.DogHolder
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.entities.Dog
import com.ort.edu.ar.parcialtp3.R

class DogListAdapter(private val dogsList: MutableList<Dog> = mutableListOf(), private val onItemClick: OnViewItemClickedListener
):RecyclerView.Adapter<DogHolder>() {

    override fun getItemCount() =dogsList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogHolder {
       val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_item_info,parent,false)
     return  (DogHolder(view))
          }

    override fun onBindViewHolder(holder: DogHolder, position: Int) {

        val dog = dogsList[position]
       holder.render(dog)

        holder.getCardLayout().setOnClickListener{
            onItemClick.onViewItemDetail(dog)
        }
    }
    fun updateData(newData: List<Dog>) {
        dogList.clear()
        dogList.addAll(newData)

        notifyDataSetChanged()
    }

}