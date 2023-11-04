package com.ort.edu.ar.parcialtp3.Data.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dog_table")
class DogEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int =0,
    @ColumnInfo(name = "breed") val breed: String,
    @ColumnInfo(name = "sub_breed") val subBreed: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "sex") val sex: String,
    @ColumnInfo(name = "age") val age: String,
    @ColumnInfo(name = "weight") val weight: String,
    @ColumnInfo(name = "favorite") val favorite: Boolean,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "owner_name") val ownerName: String,
    @ColumnInfo(name = "description") val description: String
)