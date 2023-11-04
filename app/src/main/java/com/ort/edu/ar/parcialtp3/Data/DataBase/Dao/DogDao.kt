package com.ort.edu.ar.parcialtp3.Data.DataBase.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ort.edu.ar.parcialtp3.Data.DataBase.Entities.DogEntity

@Dao
interface DogDao {
    @Query("SELECT * FROM dog_table")
    suspend fun getAllDogs():List<DogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogs:List<DogEntity>)
}