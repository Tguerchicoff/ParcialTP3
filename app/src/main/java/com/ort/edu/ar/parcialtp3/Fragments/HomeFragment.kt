package com.ort.edu.ar.parcialtp3.Fragments

import android.annotation.SuppressLint
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
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private lateinit var ageSpinner: Spinner
    private lateinit var genderSpinner: Spinner
    private lateinit var provinceSpinner: Spinner
    private lateinit var searchView: AutoCompleteTextView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recDogs = view.findViewById(R.id.rec_dogs)
        ageSpinner = view.findViewById(R.id.spinnerEdad)
        genderSpinner = view.findViewById(R.id.spinnerSexo)
        provinceSpinner = view.findViewById(R.id.spinnerUbicacion)
        searchView = view.findViewById(R.id.searchView)

        val genderValues = Sex.values().map { it.name }.toMutableList()
        val provinceValues = Provinces.values().map { it.name }.toMutableList()
        val ageValues = (1..20).map { it.toString() }.toMutableList()


        genderValues.add(0, "Sexo")
        val genderAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, genderValues)
        genderAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        genderSpinner.adapter = genderAdapter
        genderSpinner.setSelection(0)

        provinceValues.add(0, "Zona")
        val provinceAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, provinceValues)
        provinceAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        provinceSpinner.adapter = provinceAdapter
        provinceSpinner.setSelection(0)

        ageValues.add(0, "Edad")
        val ageAdapter = ArrayAdapter(requireContext(), R.layout.item_spinner, ageValues)
        ageAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
        ageSpinner.adapter = ageAdapter
        ageSpinner.setSelection(0)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            //NO HACER NADA AL IR PARA ATRAS
        }

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
        ageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                filterAndRefreshList()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // No se requiere acción específica aquí
            }
        }

        val breedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, breedList)
        searchView.setAdapter(breedAdapter)

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se necesita implementar esto
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s.toString().trim().toLowerCase() // Convierte el texto a minúsculas
                val filteredSuggestions = breedList.filter { it.toLowerCase().startsWith(text) }
                breedAdapter.clear()
                breedAdapter.addAll(filteredSuggestions)
                breedAdapter.notifyDataSetChanged()
                filterAndRefreshList()
            }

            override fun afterTextChanged(s: Editable?) {
                filterAndRefreshList()
            }
        })

        val clearFiltersTextView = view.findViewById<TextView>(R.id.wipeFilters)
        clearFiltersTextView.setOnClickListener {
            // Limpiar los filtros
            genderSpinner.setSelection(0)
            provinceSpinner.setSelection(0)
            ageSpinner.setSelection(0)
            searchView.text.clear()
            filterAndRefreshList()
            }

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

        val textEmptyList = view?.findViewById<TextView>(R.id.textEmptyList)
        if (dogList.isEmpty()) {
            textEmptyList?.visibility = View.VISIBLE
        } else {
            textEmptyList?.visibility = View.GONE
        }
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

                    breedList.add(0, "TODOS")

                    val breedAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, breedList)
                    breedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                }
            }
        }
    }

    private fun filterAndRefreshList() {
        val genderFilter = if (genderSpinner != null && genderSpinner.selectedItemPosition > 0) genderSpinner.selectedItem.toString() else null
        val provinceFilter = if (provinceSpinner != null && provinceSpinner.selectedItemPosition > 0) provinceSpinner.selectedItem.toString() else null
        val ageFilter = if(ageSpinner != null && ageSpinner.selectedItemPosition > 0) ageSpinner.selectedItem.toString().toInt() else null
        val breedFilter = if (searchView.text.toString().isNotBlank()) searchView.text.toString() else null

        val filteredList = DogProvider.getAllDogs().filter { dog ->
            (genderFilter == null || dog.gender == genderFilter) &&
                    (provinceFilter == null || dog.location == provinceFilter) &&
                    (breedFilter == null || dog.breed.contains(breedFilter, ignoreCase = true)) &&
                    (ageFilter == null || dog.age == ageFilter)
        }

        dogListAdapter.updateDogList(filteredList.toMutableList())
    }
}



