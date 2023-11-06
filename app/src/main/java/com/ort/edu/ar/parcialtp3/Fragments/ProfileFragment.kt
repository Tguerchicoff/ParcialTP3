package com.ort.edu.ar.parcialtp3.Fragments

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.ort.edu.ar.parcialtp3.Activities.MainActivity
import com.ort.edu.ar.parcialtp3.databinding.FragmentProfileBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding
//    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        _binding.btnUploadPhoto.setOnClickListener {

            val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
            chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
            chooseFile.type = "image/*"

            resultLauncher.launch(chooseFile)
        }

        if (this.activity is MainActivity) {
            val cosa : MainActivity = activity as MainActivity
            cosa.loadImageFromStorage(_binding.imViewProfile)
        }

        return _binding.root
    }


    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            data?.let {
                val imageUri = Uri.parse(data.data.toString())

                _binding.imViewProfile.setImageURI(imageUri)

                // TODO: check deprecated
                // TODO: viewmodel also will update MainActivity nav_header
                saveToInternalStorage(MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri))

//                // TODO: Check extension
//                val toCheckTypes = imageUri.toFile().absoluteFile.toString().split("/")
//                if (toCheckTypes.isNotEmpty()) {
//                    val filename = toCheckTypes[toCheckTypes.size-1]
//                    val extension = filename.drop(filename.lastIndexOf("."))
//
//                    println("extension $extension")
//
//                    if (listOf("JPG", "jpg", "JPEG", "jpeg", "PNG", "png").contains(extension)) {
//                        _binding.imViewProfile.setImageURI(imageUri)
//                        saveToInternalStorage(MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri))
//                    }
//                }

            }
        }
    }

    private fun saveToInternalStorage(bitmapImage: Bitmap): String? {
        // https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android

        val cw = ContextWrapper(this.context)
        // path to /data/data/yourapp/app_data/imageDir
        val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
        // Create imageDir
        val mypath = File(directory, "profile.jpg")
        var fos: FileOutputStream? = null

        try {
            fos = FileOutputStream(mypath)
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        return directory.absolutePath
    }

}