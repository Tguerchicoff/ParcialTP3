package com.ort.edu.ar.parcialtp3.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdoptedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is adopted Fragment"
    }
    val text: LiveData<String> = _text
}