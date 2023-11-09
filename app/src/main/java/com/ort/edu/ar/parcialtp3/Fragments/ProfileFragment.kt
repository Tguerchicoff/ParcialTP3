package com.ort.edu.ar.parcialtp3.Fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ort.edu.ar.parcialtp3.Activities.MainActivity
import com.ort.edu.ar.parcialtp3.ViewModels.ProfileViewModel
import com.ort.edu.ar.parcialtp3.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

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

        profileViewModel = ViewModelProvider(this.activity as MainActivity)[ProfileViewModel::class.java]

        if (this.activity is MainActivity) {
            val mainActivity : MainActivity = activity as MainActivity

            val sharedPreferences = mainActivity.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
            _binding.tvProfilename.setText(sharedPreferences.getString("user_name", ""))
        }

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.getUserImage().observe(requireActivity()) { bitmap ->
            _binding.imViewProfile.setImageBitmap(bitmap)
        }

        profileViewModel.fetchUserProfileImage(requireActivity().baseContext)
    }


    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data

            data?.let {
                val imageUri = Uri.parse(data.data.toString())

                // TODO: check deprecated
                profileViewModel.updateUserProfileImage(MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri), requireActivity().baseContext)
            }
        }
    }
}