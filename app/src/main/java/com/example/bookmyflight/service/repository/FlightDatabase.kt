package com.example.bookmyflight.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.service.FlightDao


@Database(entities = arrayOf(FlightModel::class), version = 1)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun flightDao() : FlightDao

    //Singleton
    companion object{

        @Volatile private var instance : FlightDatabase? = null

        private val lock = Any()

        operator fun invoke(context:Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(contex:Context) = Room.databaseBuilder(
            contex.applicationContext,FlightDatabase::class.java,"flightdatabase").build()

    }


}