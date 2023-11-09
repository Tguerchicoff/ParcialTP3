package com.ort.edu.ar.parcialtp3.Entities

import java.io.Serializable


data class Dog(
    val name: String,
    val breed: String,
    val subBreed: String,
    val age: Int,
    val gender: String,
    val description: String,
    val weight: Double,
    val location: String,
    val urlImage1: String,
    val urlImage2: String?,
    val urlImage3: String?,
    val detail: String?,
    var caregiversName: String?,
    val isAdopted: Boolean,
    var isFavorite: Boolean
): Serializable
