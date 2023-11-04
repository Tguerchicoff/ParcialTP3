package com.ort.edu.ar.parcialtp3.Domain.Model

import androidx.room.ColumnInfo
import com.ort.edu.ar.parcialtp3.Data.DataBase.Entities.DogEntity

data class Dog (val breed: String,
                val subBreed: String,
                val name: String,
                val photo: String,
                val sex: String,
                val age: String,
                val weight: String,
                val favorite: Boolean,
                val location: String,
                val ownerName: String,
                val description: String)

fun DogEntity.toDomain() = Dog(breed,subBreed,name,photo,sex,age,weight
                                    ,favorite,location,ownerName, description)