package com.ort.edu.ar.parcialtp3.Fragments
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ort.edu.ar.parcialtp3.ImagePagerAdapter
import com.ort.edu.ar.parcialtp3.R

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

        viewPager = view.findViewById(R.id.detailsFgViewPager)
        imageList = listOf(R.drawable.dachshund_dog, R.drawable.dog_paw, R.drawable.chat)

        val adapter = ImagePagerAdapter(imageList)
        viewPager.adapter = adapter

        val phoneImageView = view.findViewById<ImageView>(R.id.detailsFgPhoneImageView)

        // Agrega un OnClickListener para abrir el panel de llamada
        phoneImageView.setOnClickListener {
            val phoneNumber = "123456789" // Harcodeado
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intent)
        }

        return view
    }
}