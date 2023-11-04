package com.ort.edu.ar.parcialtp3.Data.Network

import com.ort.edu.ar.parcialtp3.Data.DataBase.Dao.DogDao
import com.ort.edu.ar.parcialtp3.Domain.Model.Dog
import com.ort.edu.ar.parcialtp3.Domain.Model.toDomain
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val dogDao: DogDao
){
    suspend fun getAllDogsFromDatabase():List<Dog>{
        val response = dogDao.getAllDogs()
        return response.map {it.toDomain() }
    }
}