package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.Services.ActivityServiceApiBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PublicationFragment : Fragment() {

    private val dogApiService = ActivityServiceApiBuilder.create()
    private val dogImagesList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_publication, container, false)

        // Inflate the layout for this fragment
        val btnCargarImg = root.findViewById<Button>(R.id.btn_cargar_img)
        btnCargarImg.setOnClickListener {
            getDogsImages()
        }

        return root
    }

    fun loadImagesWithGlide(imageUrls: List<String>, imageViews: List<ImageView>) {
        for (i in imageUrls.indices) {
            val imageUrl = imageUrls[i]
            val imageView = imageViews[i]

            Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView)
        }
    }
    fun getDogsImages() {
        GlobalScope.launch(Dispatchers.IO) {
            //HACER QUE TRAIGA EL BREED Y SUBBREED A PARTIR DE LA SELECCION DEL DROP DOWN LIST DE RAZA Y SUB RAZA
            val breed = "bulldog"
            val subBreed = "french"

            val response = if (subBreed != null && subBreed.isNotEmpty()) {
                dogApiService.getThreeRandomSubBreedImages(breed, subBreed).execute()
            } else {
                dogApiService.getThreeRandomBreedImages(breed).execute()
            }

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    dogImagesList.addAll(body.message)

                    if (dogImagesList.size >= 3) {
                        requireActivity().runOnUiThread {
                            val imageViews = listOf(
                                view?.findViewById(R.id.image_upload_1) as ImageView,
                                view?.findViewById(R.id.image_upload_2) as ImageView,
                                view?.findViewById(R.id.image_upload_3) as ImageView
                            )

                            loadImagesWithGlide(dogImagesList.subList(0, 3), imageViews)
                        }
                    }
                }
            }
        }
    }
}
