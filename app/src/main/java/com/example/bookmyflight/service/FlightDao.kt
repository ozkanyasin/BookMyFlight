package com.example.bookmyflight.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookmyflight.mode.FlightModel

@Dao
interface FlightDao {

    //data access object

    @Insert
    suspend fun insertAll(vararg flights: FlightModel) : ArrayList<Long>

    //suspend -> coroutine, pause&resume
    //vararg -> farklı sayıdaki obje değişkeni
    //list long primary keyi döndürecek

    @Query("SELECT*FROM flightmodel")
    suspend fun getAllFlights() : ArrayList<FlightModel>

    @Query("SELECT*FROM flightmodel WHERE uuid =:flightId")
    suspend fun getFligth(flightId: Int) : FlightModel

    @Query("DELETE FROM flightmodel")
    suspend fun deleteAllFlight()

    companion object{}

}