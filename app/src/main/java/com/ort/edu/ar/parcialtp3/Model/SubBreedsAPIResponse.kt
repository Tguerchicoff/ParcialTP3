package com.ort.edu.ar.parcialtp3.Model

data class SubBreedsAPIResponse(
    val message: List<String>,
    val status: String
)
//message es una lista de strings que representa las subrazas, y status es una cadena que indica el estado
// de la solicitud ("success" en este caso).