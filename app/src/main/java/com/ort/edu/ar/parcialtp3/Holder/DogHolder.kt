package com.ort.edu.ar.parcialtp3.Holder

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.R
import com.bumptech.glide.Glide
import com.ort.edu.ar.parcialtp3.entities.Dog

class DogHolder (v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

       fun render (dog: Dog){

           setName(dog.name)
           setBreed(dog.breed)
           setSubBreed(dog.subBreed)
           setAge(dog.age)
           setGender(dog.gender)
           setImage(dog.urlImage1)
    }

    init {
        this.view = v
    }


    fun setName(name: String) {
        val txt: TextView = view.findViewById(R.id.txtCardName)
        txt.text = name
    }

    fun setBreed(breed: String){
        val txt: TextView = view.findViewById(R.id.txtCardBreed)
        txt.text = breed
    }

    fun setSubBreed(subBreed: String){
        val txt: TextView = view.findViewById(R.id.txtCardSubBreed)
        txt.text = subBreed
    }

    fun setAge(age: Int){
        val txt: TextView = view.findViewById(R.id.txtCardAge)
        txt.text = "$age"
    }

    fun setGender(gender: String){
        val txt: TextView = view.findViewById(R.id.txtCardGenre)
        txt.text = gender
    }

    fun setImage(imageUrl1: String){
        val imageView: ImageView = view.findViewById(R.id.imageView)
        Glide.with(view.context)
            .load(imageUrl1)
            .fitCenter()
            .into(imageView)
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.card)
    }
}
