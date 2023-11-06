package com.ort.edu.ar.parcialtp3.Services

import com.ort.edu.ar.parcialtp3.Model.AllBreedsAPIResponse
import com.ort.edu.ar.parcialtp3.Model.RandomImageAPIResponse
import com.ort.edu.ar.parcialtp3.Model.RandomImagesAPIResponse
import com.ort.edu.ar.parcialtp3.Model.SubBreedImagesAPIResponse
import com.ort.edu.ar.parcialtp3.Model.SubBreedsAPIResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
interface DogApiService {
    //List all breeds
    @GET("breeds/list/all")
    fun getBreeds(): Response<AllBreedsAPIResponse>

    //Random image
    @GET("breeds/image/random")
    fun getRandomImage(): Response<RandomImageAPIResponse>

    @GET("breeds/image/random/{count}")
    fun getRandomImages(@Path("count") count: Int): Response<RandomImagesAPIResponse>

    //By breed
    @GET("breed/{breed}/images")
    fun getImagesForBreed(@Path("breed") breed: String): Response<SubBreedImagesAPIResponse>
    @GET("breed/{breed}/images/random")
    fun getRandomHoundImage(): Response<RandomImageAPIResponse>
    @GET("breed/{breed}/images/random/{count}")
    fun getRandomHoundImages(@Path("count") count: Int): Response<RandomImagesAPIResponse>

    //By sub-breed
    @GET("breed/{breed}/list")
    fun getSubBreedsForHound(): Response<SubBreedsAPIResponse>

    @GET("breed/{breed}/{subBreed}/images")
    fun getImagesForSubBreed(@Path("subBreed") subBreed: String): Response<SubBreedImagesAPIResponse>

    @GET("breed/{breed}/{subBreed}/images/random")
    fun getRandomImageForSubBreed(@Path("subBreed") subBreed: String): Response<RandomImageAPIResponse>

    @GET("breed/{breed}/{subBreed}/images/random/{count}")
    fun getRandomImagesForSubBreed(@Path("subBreed") subBreed: String, @Path("count") count: Int): Response<RandomImagesAPIResponse>

}