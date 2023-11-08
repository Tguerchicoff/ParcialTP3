package com.ort.edu.ar.parcialtp3.Model

data class SubBreedImagesAPIResponse(
    val message: List<String>,  //message es una lista de URLs de imágenes de subrazas de perros
    val status: String
)
//La respuesta JSON de ese endpoint incluye una lista de URLs de imágenes de subrazas de perros en la propiedad
// message y una cadena que indica el estado de la solicitud en la propiedad status