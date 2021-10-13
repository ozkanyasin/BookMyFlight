package com.example.bookmyflight.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.model.AirlineModel
import com.example.bookmyflight.service.repository.FlightRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepartureViewModel(val repository: FlightRepository) : ViewModel() {

    val flightList = MutableLiveData<FlightModel>()
    val flightLoading = MutableLiveData<Boolean>()


    fun getFlights(){

        val response = repository.getFlightsByDirection("D")
        response.enqueue(object : Callback<FlightModel> {
            override fun onResponse(call: Call<FlightModel>, response: Response<FlightModel>) {
                Log.i("asd",response.body().toString())
                flightList.postValue(response.body())

            }

            override fun onFailure(call: Call<FlightModel>, t: Throwable) {
                t.printStackTrace()
                /*Toast.makeText(,"Bir Hata Oluştu!",Toast.LENGTH_LONG)*/
            }
        })

    }

    private fun storeInRoom(){

    }

}