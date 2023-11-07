package com.ort.edu.ar.parcialtp3.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ort.edu.ar.parcialtp3.entities.Dog

class DogViewModel : ViewModel() {
    private val dogListLiveData = MutableLiveData<MutableList<Dog>>()
    val dogList: LiveData<MutableList<Dog>> = dogListLiveData

    fun updateDogList(newDogList: MutableList<Dog>) {
        dogListLiveData.value = newDogList
    }
}