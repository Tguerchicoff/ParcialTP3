package com.ort.edu.ar.parcialtp3.Fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomappbar.BottomAppBar
import com.ort.edu.ar.parcialtp3.Adapters.DogListAdapter
import com.ort.edu.ar.parcialtp3.Listener.OnViewItemClickedListener
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.Services.ActivityServiceApiBuilder
import com.ort.edu.ar.parcialtp3.entities.Sex
import com.ort.edu.ar.parcialtp3.entities.Provinces
import com.ort.edu.ar.parcialtp3.entities.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), OnViewItemClickedListener {
    private lateinit var dogListAdapter: DogListAdapter
    lateinit var recDogs: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private val dogApiService = ActivityServiceApiBuilder.create()
    private var breedList: MutableList<String> = mutableListOf()
    private lateinit var breedSpinner: Spinner
    private lateinit var genderSpinner: Spinner
    private lateinit var provinceSpinner: Spinner
    private lateinit var searchView: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recDogs = view.findViewById(R.id.rec_dogs)
        breedSpinner = view.findViewById(R.id.spinnerRaza)
        genderSpinner = view.findViewById(R.id.spinnerSexo)
        provinceSpinner = view.findViewById(R.id.spinner1)
        searchView = view.findViewById(R.id.searchView)

        val genderValues = Sex.values().map { it.name }.toMutableList()
        val provinceValues = Provinces.values().map { it.name }.toMutableList()

        // Agrega el hint vacío en la posición 0
        genderValues.add(0, "SEXO")
        provinceValues.add(0, "UBI")

        val genderAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, genderValues)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = genderAdapter
        genderSpinner.setSelection(0)

        val provinceAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, provinceValues)
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        provinceSpinner.adapter = provinceAdapter
        provinceSpinner.setSelection(0)

        // Llamar a la función de obtención de razas
        listDogBreeds()

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterAndRefreshList()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se requiere acción específica aquí
            }
        }

        provinceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                filterAndRefreshList()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se requiere acción específica aquí
            }
        }

        // Configura un adaptador para el AutoCompleteTextView
        val breedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, breedList)
        searchView.setAdapter(breedAdapter)

        // Agrega un listener para actualizar las sugerencias mientras se escribe
        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar esto
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filtra las sugerencias basadas en el texto ingresado
                val text = s.toString().trim()
                val filteredSuggestions = breedList.filter { it.contains(text, ignoreCase = true) }
                breedAdapter.clear()
                breedAdapter.addAll(filteredSuggestions)
                breedAdapter.notifyDataSetChanged()
            }

            override fun afterTextChanged(s: Editable?) {
                // No se necesita implementar esto
            }
        })

        return view
    }

    override fun onStart() {
        super.onStart()
        val dogList = DogProvider.getAllDogs().toMutableList()

        requireActivity()
        recDogs.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        dogListAdapter = DogListAdapter(dogList, this)
        recDogs.layoutManager = linearLayoutManager
        recDogs.adapter = dogListAdapter
    }

    override fun onViewItemDetail(dog: Dog) {
        val detailsFragment = DetailsFragment()

        val bundle = Bundle()
        bundle.putSerializable("dog", dog)
        detailsFragment.arguments = bundle

        // Reemplazar el contenido actual del fragmento principal con el fragmento de detalles
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, detailsFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun listDogBreeds() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = dogApiService.getBreeds().execute()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    breedList = body.message.keys.toList().toMutableList()
                    breedList.sort()

                    // Agregar una entrada en blanco o "Seleccione una raza" al principio de la lista
                    breedList.add(0, "RAZA")

                    // Configura el adaptador del Spinner con las razas reales
                    val breedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breedList)
                    breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    // Asigna el adaptador al Spinner
                    requireActivity().runOnUiThread {
                        breedSpinner.adapter = breedAdapter
                    }
                }
            }
        }
    }

    private fun filterAndRefreshList() {
        val genderFilter = if (genderSpinner != null && genderSpinner.selectedItemPosition > 0) genderSpinner.selectedItem.toString() else null
        val provinceFilter = if (provinceSpinner != null && provinceSpinner.selectedItemPosition > 0) provinceSpinner.selectedItem.toString() else null
        val breedFilter = if (searchView.text.toString().isNotBlank()) searchView.text.toString() else null

        val filteredList = DogProvider.getAllDogs().filter { dog ->
            (genderFilter == null || dog.gender == genderFilter) &&
                    (provinceFilter == null || dog.location == provinceFilter) &&
                    (breedFilter == null || dog.breed == breedFilter)
        }

        dogListAdapter.updateDogList(filteredList.toMutableList())
    }
}



