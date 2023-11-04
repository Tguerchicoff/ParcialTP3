package com.ort.edu.ar.parcialtp3.Services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ActivityServiceApiBuilder {

    private val BASE_URL = "https://dog.ceo/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): DogApiService {
        return retrofit.create(DogApiService::class.java)
    }
}