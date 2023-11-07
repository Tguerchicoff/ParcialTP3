package com.ort.edu.ar.parcialtp3.Services

import com.ort.edu.ar.parcialtp3.Model.AllBreedsAPIResponse
import com.ort.edu.ar.parcialtp3.Model.RandomImageAPIResponse
import com.ort.edu.ar.parcialtp3.Model.RandomImagesAPIResponse
import com.ort.edu.ar.parcialtp3.Model.SubBreedImagesAPIResponse
import com.ort.edu.ar.parcialtp3.Model.SubBreedsAPIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
interface DogApiService {
    //List all breeds
    @GET("breeds/list/all")
    fun getBreeds(): Call<AllBreedsAPIResponse>

    //Random image
    @GET("breeds/image/random")
    fun getRandomImage(): Call<RandomImageAPIResponse>

    @GET("breeds/image/random/{count}")
    fun getRandomImages(@Path("count") count: Int): Call<RandomImagesAPIResponse>

    //By breed
    @GET("breed/{breed}/images")
    fun getImagesForBreed(@Path("breed") breed: String): Call<SubBreedImagesAPIResponse>
    @GET("breed/{breed}/images/random")
    fun getRandomHoundImage(): Call<RandomImageAPIResponse>
    @GET("breed/{breed}/images/random/{count}")
    fun getRandomHoundImages(@Path("count") count: Int): Call<RandomImagesAPIResponse>

    //By sub-breed
    @GET("breed/{breed}/list")
    fun getSubBreedsForHound(): Call<SubBreedsAPIResponse>

    @GET("breed/{breed}/{subBreed}/images")
    fun getImagesForSubBreed(@Path("subBreed") subBreed: String): Call<SubBreedImagesAPIResponse>

    @GET("breed/{breed}/{subBreed}/images/random")
    fun getRandomImageForSubBreed(@Path("subBreed") subBreed: String): Call<RandomImageAPIResponse>

    @GET("breed/{breed}/{subBreed}/images/random/{count}")
    fun getRandomImagesForSubBreed(@Path("subBreed") subBreed: String, @Path("count") count: Int): Call<RandomImagesAPIResponse>
    @GET("breed/{breed}/{subBreed}/images/random/3")
    fun getThreeRandomSubBreedImages(@Path("breed") breed: String, @Path("subBreed") subBreed: String): Call<RandomImagesAPIResponse>
    @GET("breed/{breed}/images/random/3")
    fun getThreeRandomBreedImages(@Path("breed") breed: String): Call<RandomImagesAPIResponse>

}