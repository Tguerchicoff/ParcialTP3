package com.ort.edu.ar.parcialtp3.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ort.edu.ar.parcialtp3.R
import com.bumptech.glide.Glide

class DogHolder (v: View) : RecyclerView.ViewHolder(v) {
    private var view: View

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

    fun setImage(imageUrl: String){
        val imageView: ImageView = view.findViewById(R.id.imageView)
        Glide.with(view.context)
            .load(imageUrl)
            .into(imageView)
    }

    fun getCardLayout (): CardView {
        return view.findViewById(R.id.card)
    }
}
