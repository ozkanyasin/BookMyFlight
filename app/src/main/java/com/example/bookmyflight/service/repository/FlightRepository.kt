package com.example.bookmyflight.service.repository

import com.example.bookmyflight.mode.Flight
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.service.IService
import io.reactivex.Observable
import io.reactivex.Single

class FlightRepository(private val flightService: IService){



    fun getFlightsByDate(date :String, direction: String) = flightService.getFlightListByDate(date,direction)

    fun getFlightsByDirection(direction :String) = flightService.getFlightListByDirection(direction)

    fun getData() : Observable<FlightModel> {
        return flightService.getFlightListByRxJava()
    }



}