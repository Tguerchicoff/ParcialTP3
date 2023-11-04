package com.ort.edu.ar.parcialtp3.entities

import android.os.Parcel
import android.os.Parcelable


class Dog(
    name: String?,
    breed: String?,
    subBreed: String?,
    age: Int?,
    gender: String?,
    description: String?,
    weight: Double?,
    location: String?,
    urlImage1: String?,
    urlImage2: String?,
    caregiversName: String?

) : Parcelable {
    var name: String = ""
    var breed: String = ""
    var subBreed: String = ""
    var age: Int = 0
    var gender: String = ""
    var desciption: String = ""
    var weight: Double = 0.0
    var location: String = ""
    var urlImage1: String = ""
    var urlImage2: String = ""
    var caregiversName: String = ""


    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
    )

    class Gender {
        companion object {
            val male = "Macho"
            val female= "Hembra"
        }
    }

       override fun writeToParcel(parcel: Parcel, flags: Int) {
           parcel.writeString(name)
           parcel.writeString(breed)
           parcel.writeString(subBreed)
           parcel.writeInt(age)
           parcel.writeString(gender)
           parcel.writeString(desciption)
           parcel.writeDouble(weight)
           parcel.writeString(location)
           parcel.writeString(urlImage1)
           parcel.writeString(urlImage2)
           parcel.writeString(caregiversName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dog> {
        override fun createFromParcel(parcel: Parcel): Dog {
            return Dog(parcel)
        }

        override fun newArray(size: Int): Array<Dog?> {
            return arrayOfNulls(size)
        }
    }
}
