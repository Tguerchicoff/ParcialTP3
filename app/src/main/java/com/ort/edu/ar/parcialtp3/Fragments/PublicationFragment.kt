package com.ort.edu.ar.parcialtp3.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.Services.ActivityServiceApiBuilder
import com.ort.edu.ar.parcialtp3.entities.Dog
import com.ort.edu.ar.parcialtp3.entities.Provinces
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
class PublicationFragment : Fragment() {

    private val dogApiService = ActivityServiceApiBuilder.create()
    private val dogImagesList = ArrayList<String>()
    private lateinit var radioHembra: RadioButton
    private lateinit var radioMacho: RadioButton
    private lateinit var breedSpinner: Spinner
    private lateinit var subBreedSpinner: Spinner
    private lateinit var locationSpinner: Spinner
    private lateinit var nombreEditText: EditText
    private lateinit var edadEditText: EditText
    private lateinit var pesoEditText: EditText
    private lateinit var descripcionEditText: EditText
    private lateinit var sexoRadioGroup: RadioGroup
    private var generoSeleccionado: String? = null
    private lateinit var loading1: ProgressBar
    private lateinit var loading2: ProgressBar
    private lateinit var loading3: ProgressBar
    private var searched = false

    private var breedList: MutableList<String> = mutableListOf()
    private val subBreedList: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_publication, container, false)

        nombreEditText = root.findViewById(R.id.edit_text_nombre)
        edadEditText = root.findViewById(R.id.edit_text_edad)
        pesoEditText = root.findViewById(R.id.edit_text_peso)
        descripcionEditText = root.findViewById(R.id.edit_text_descripcion)
        sexoRadioGroup = root.findViewById(R.id.radio_group_sexo)

        radioHembra = root.findViewById(R.id.radio_hembra)
        radioMacho = root.findViewById(R.id.radio_macho)

        breedSpinner = root.findViewById(R.id.spinner_raza)
        subBreedSpinner = root.findViewById(R.id.spinner_subraza)
        this.listDogBreeds()

        locationSpinner = root.findViewById(R.id.spinner_ubicacion)

        loading1 = root.findViewById(R.id.loader1)
        loading2 = root.findViewById(R.id.loader2)
        loading3 = root.findViewById(R.id.loader3)



        val locations = DogProvider.getLocations()
        val locationAdapter = ArrayAdapter(requireContext(), R.layout.item_publication_spinner, Provinces.values().map { it.formattedName })
        locationAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)


        locationSpinner.adapter = locationAdapter


        radioHembra.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                radioMacho.isChecked = false
                generoSeleccionado = "Hembra"
            }
        }
        radioMacho.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                radioHembra.isChecked = false
                generoSeleccionado = "Macho"
            }
        }

        val btnCargarImg = root.findViewById<Button>(R.id.btn_cargar_img)
        btnCargarImg.setOnClickListener {

            if(breedSpinner.selectedItemPosition > 0){
                searched = true
                dogImagesList.clear()
                showLoaders(true)
                getDogsImages()
            }
            else{
                Toast.makeText(requireContext(), "Selecciona una raza", Toast.LENGTH_LONG).show()
            }
        }

        val btnSubmit = root.findViewById<Button>(R.id.btn_enviar)
        btnSubmit.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val edadText = edadEditText.text.toString()
            val pesoText = pesoEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val ubicacion = locationSpinner.selectedItem.toString()
            val genero = generoSeleccionado ?: "Desconocido"

            if (nombre.isNotEmpty() && edadText.isNotEmpty() && pesoText.isNotEmpty() && descripcion.isNotEmpty() && ubicacion.isNotEmpty() && searched){
                try {
                    val edad = edadText.toInt()
                    val peso = pesoText.toDouble()
                    val subBreed = if (subBreedSpinner.selectedItem != null) {
                        subBreedSpinner.selectedItem.toString()
                    }else{
                        ""
                    }
                    submitDogForm(nombre, breedSpinner.selectedItem.toString(), subBreed, edad, genero, descripcion, peso, ubicacion, dogImagesList)
                    nombreEditText.text.clear()
                    edadEditText.text.clear()
                    pesoEditText.text.clear()
                    descripcionEditText.text.clear()
                    radioHembra.isChecked = false
                    radioMacho.isChecked = false
                    dogImagesList.clear()
                    val imageViews = listOf(
                        view?.findViewById(R.id.image_upload_1) as ImageView,
                        view?.findViewById(R.id.image_upload_2) as ImageView,
                        view?.findViewById(R.id.image_upload_3) as ImageView
                    )
                    imageViews.forEach { imageView ->
                        imageView.setImageDrawable(null)
                    }
                    locationSpinner.setSelection(0)
                    breedSpinner.setSelection(0)


                    Toast.makeText(requireContext(), "Perro guardado correctamente", Toast.LENGTH_LONG).show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(requireContext(), "La edad y el peso deben ser números válidos", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos obligatorios", Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    private fun loadImagesWithGlide(imageUrls: List<String>, imageViews: List<ImageView>) {
        for (i in imageUrls.indices) {
            val imageUrl = imageUrls[i]
            val imageView = imageViews[i]

            Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView)
        }
    }

    fun submitDogForm(name: String, breed: String, subBreed: String, age: Int, gender: String, description: String, weight: Double, location: String, images: List<String>) {
        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "Usuario")

        // Crear una instancia de Dog
        val newDog = Dog(name, breed, subBreed, age, gender, description, weight, location, images[0], images.getOrNull(1), images.getOrNull(2), detail = null, caregiversName = userName, isAdopted = false, isFavorite = false)

        // Agregar el nuevo perro a DogProvider
        DogProvider.addDog(newDog)
    }

    private fun listDogBreeds() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = dogApiService.getBreeds().execute()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    breedList = body.message.keys.toList().toMutableList()
                    breedList.sort()
                    breedList.add(0, "Raza")
                    val breedAdapter = ArrayAdapter(
                        requireContext(),
                        R.layout.item_publication_spinner,
                        breedList
                    )
                    breedAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)


                    requireActivity().runOnUiThread {
                        breedSpinner.adapter = breedAdapter


                        breedSpinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    val selectedBreed = breedList[position]

                                    val subBreeds = body.message[selectedBreed]

                                    subBreedList.clear()

                                    if (subBreeds != null && subBreeds.isNotEmpty()) {
                                        subBreedList.addAll(subBreeds)
                                        val subBreedAdapter = ArrayAdapter(
                                            requireContext(),
                                            R.layout.item_publication_spinner,
                                            subBreedList
                                        )
                                        subBreedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                                        subBreedSpinner.adapter = subBreedAdapter
                                        subBreedSpinner.visibility = View.VISIBLE
                                    } else {
                                        subBreedSpinner.visibility = View.GONE
                                    }
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                }
                            }
                    }
                }
            }
        }
    }
    private fun getDogsImages() {
        GlobalScope.launch(Dispatchers.IO) {

            val breed = breedSpinner.selectedItem.toString()

            var subBreed = ""

            if (subBreedSpinner.visibility == View.VISIBLE) {
                subBreed = subBreedSpinner.selectedItem.toString()
            }

            val response = if (subBreed.isNotEmpty()) {
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
                            showLoaders(false)
                        }
                    }
                }            } else {
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Error de API al cargar las imágenes", Toast.LENGTH_SHORT).show()
                    showLoaders(false)
                }

            }
        }
    }

    private fun showLoaders(isLoading: Boolean) {
        loading1.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        loading2.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
        loading3.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
    }
}


