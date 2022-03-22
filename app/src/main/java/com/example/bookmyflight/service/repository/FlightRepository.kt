package com.example.bookmyflight.service.repository

import com.example.bookmyflight.service.IService


class FlightRepository(private val flightService: IService){

    fun getFlightsByDate(date :String, direction: String) = flightService.getFlightListByDate(date,direction)

    fun getAirline(iata: String) = flightService.getAirline(iata)

    fun getFlight(id: String) = flightService.getFlight(id)

}