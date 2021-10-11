package com.example.bookmyflight.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.service.repository.FlightRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArrivalViewModel(val repository: FlightRepository) : ViewModel()  {

    val flightList = MutableLiveData<FlightModel>()
    val flightLoading = MutableLiveData<Boolean>()


    fun refreshData() {

    }

    fun getAllFlights(){

        val response = repository.getAllFlights()
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

    fun getFlightsByDirection(direction :String){

        val response = repository.getFlightsByDirection(direction)
        response.enqueue(object :Callback<FlightModel>{
            override fun onResponse(call: Call<FlightModel>, response: Response<FlightModel>) {
                flightList.postValue(response.body())
            }

            override fun onFailure(call: Call<FlightModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }

    fun getFlightsByDate(date :String){
        val response = repository.getFlightsByDate(date)
        response.enqueue(object :Callback<FlightModel>{
            override fun onResponse(call: Call<FlightModel>, response: Response<FlightModel>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<FlightModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


}