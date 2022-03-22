package com.example.bookmyflight.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookmyflight.service.repository.FlightRepository

class MyViewModelFactory(private val repository: FlightRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ArrivalViewModel::class.java)) {
            ArrivalViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(DepartureViewModel::class.java)){
            DepartureViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)){
            DetailViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}