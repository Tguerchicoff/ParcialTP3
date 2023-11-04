package com.ort.edu.ar.parcialtp3.Model

data class AllBreedsAPIResponse(
    val message: Map<String, List<String>>,
    val status: String
)

// La respuesta JSON incluye: un objeto message donde las claves son los nombres de las razas y los valores son
// listas de subrazas, y un campo status que indica el estado de la solicitud.
