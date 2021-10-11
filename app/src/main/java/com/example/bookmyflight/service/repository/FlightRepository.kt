package com.example.bookmyflight.service.repository

import com.example.bookmyflight.service.IService

class FlightRepository(private val flightService: IService){


    fun getAllFlights() = flightService.getFlightList()

    fun getFlightsByDate(date :String) = flightService.getFlightListByDate(date)

    fun getFlightsByDirection(direction :String) = flightService.getFlightListByDirection(direction)


}