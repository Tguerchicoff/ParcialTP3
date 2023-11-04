package com.ort.edu.ar.parcialtp3.DependencyInyection

import android.content.Context
import androidx.room.Room
import com.ort.edu.ar.parcialtp3.Data.DataBase.DogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    const val DOG_DATABASE_NAME = "dog_database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, DogDatabase::class.java, DOG_DATABASE_NAME).build()
    @Singleton
    @Provides
    fun provideDogDao(db:DogDatabase) = db.getDogDao()


}