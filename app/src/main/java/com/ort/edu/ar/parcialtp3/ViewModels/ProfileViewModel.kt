package com.ort.edu.ar.parcialtp3.ViewModels

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ort.edu.ar.parcialtp3.R
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ProfileViewModel: ViewModel() {

    var userProfileImage: MutableLiveData<Bitmap> = MutableLiveData()

    fun getUserImage(): MutableLiveData<Bitmap> {
        return userProfileImage
    }

    fun fetchUserProfileImage(context: Context) {
        // https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android

        try {
            val cw = ContextWrapper(context)
            // path to /data/data/yourapp/app_data/imageDir
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            val f = File(directory, "profile.jpg")
            val b = BitmapFactory.decodeStream(FileInputStream(f))

            userProfileImage.postValue(b);
        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
            // Si no hay imagen guardada, ponemos la imagen por defecto
            userProfileImage.value = context.resources.getDrawable(R.drawable.user_logged, context.theme).toBitmap()
        }
    }

    fun updateUserProfileImage(bitmapImage: Bitmap, context: Context) {
        // https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android

        val cw = ContextWrapper(context)
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

        fetchUserProfileImage(context)
    }
}