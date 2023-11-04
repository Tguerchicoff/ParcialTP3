package com.ort.edu.ar.parcialtp3.Data.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ort.edu.ar.parcialtp3.Data.DataBase.Dao.DogDao
import com.ort.edu.ar.parcialtp3.Data.DataBase.Entities.DogEntity

@Database(entities = [DogEntity::class],version =1)
abstract class DogDatabase: RoomDatabase(){
    abstract fun getDogDao():DogDao
}