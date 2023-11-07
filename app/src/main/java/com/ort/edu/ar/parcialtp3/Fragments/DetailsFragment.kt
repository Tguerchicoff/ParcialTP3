package com.ort.edu.ar.parcialtp3.Fragments
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomappbar.BottomAppBar
import com.ort.edu.ar.parcialtp3.ImagePagerAdapter
import com.ort.edu.ar.parcialtp3.R
import com.ort.edu.ar.parcialtp3.entities.Dog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class DetailsFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var imageList: List<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        val dog = arguments?.getSerializable("dog") as Dog


        val bottomAppBar = requireActivity().findViewById<BottomAppBar>(R.id.bottomAppBar)
        bottomAppBar.visibility = View.GONE
        val buttonBack = view.findViewById<Button>(R.id.buttonBack)
        val adoptButton = view.findViewById<Button>(R.id.buttonAdopt)
        val checkBoxFav = view.findViewById<CheckBox>(R.id.checkBoxFav)

        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "Usuario")
        viewPager = view.findViewById(R.id.detailsFgViewPager)
        imageList = listOf(R.drawable.dachshund_dog, R.drawable.dog_paw, R.drawable.chat)


        val adapter = ImagePagerAdapter(listOf(dog.urlImage1, dog.urlImage2, dog.urlImage3))
        viewPager.adapter = adapter

        val phoneImageView = view.findViewById<ImageView>(R.id.detailsFgPhoneImageView)


        // OnClickListener para abrir el panel de llamada
        phoneImageView.setOnClickListener {
            val phoneNumber = "123456789" // Harcodeado
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }

        adoptButton.setOnClickListener {
            showAdoptionConfirmation(dog)
            bottomAppBar.visibility = View.VISIBLE
            val fragmentManager = (view.context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, AdoptedFragment()) // Reemplaza R.id.fragmentContainer con el ID de tu contenedor de fragmentos de inicio
            fragmentTransaction.addToBackStack(null) // Agrega la transacción a la pila de retroceso si lo deseas
            fragmentTransaction.commit()
        }
        ///
        checkBoxFav.isChecked = dog.isFavorite
        checkBoxFav.setOnCheckedChangeListener { _, isChecked ->
            DogProvider.toggleFavoriteStatus(dog)
        }
        buttonBack.setOnClickListener {
           // findNavController().navigate(R.id.action_detailsFragment_to_navigation_home)
            bottomAppBar.visibility = View.VISIBLE
            val fragmentManager = (view.context as AppCompatActivity).supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, HomeFragment()) // Reemplaza R.id.fragmentContainer con el ID de tu contenedor de fragmentos de inicio
            fragmentTransaction.addToBackStack(null) // Agrega la transacción a la pila de retroceso si lo deseas
            fragmentTransaction.commit()
       }


        fun updateDogDetails(dog: Dog) {
            view?.findViewById<TextView>(R.id.detailsFgTextViewNombre)?.text = dog.name
            view?.findViewById<TextView>(R.id.detailsFgTextViewLocalization)?.text = dog.location
            view?.findViewById<TextView>(R.id.detailsFgTextViewEdad)?.text = dog.age.toString()
            view?.findViewById<TextView>(R.id.detailsFgTextViewSex)?.text = dog.gender
            view?.findViewById<TextView>(R.id.detailsFgTextVieWeight)?.text = dog.weight.toString()
            view?.findViewById<TextView>(R.id.detailsFgTextVieText)?.text = dog.description
            view?.findViewById<TextView>(R.id.detailsFgTextVieNameOwner)?.text = userName
            view?.findViewById<TextView>(R.id.detailsFgTextViewBreed)?.text = dog.breed
        }

        updateDogDetails(dog)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    //AGREGO
    private fun showAdoptionConfirmation(dog: Dog) {
        val congratulationsMessage = "¡Felicidades! Has adoptado a ${dog.name}."
        Toast.makeText(requireContext(), congratulationsMessage, Toast.LENGTH_LONG).show()

        // Agrego el perro a la lista getAdoptedDogs
        DogProvider.toggleAdoptedStatus(dog)
        // Elimino el perro de la lista getAllDogs
        DogProvider.removeFromAllDogs(dog)
    }
}