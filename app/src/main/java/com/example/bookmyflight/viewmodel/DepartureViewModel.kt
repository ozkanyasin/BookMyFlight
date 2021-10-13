package com.example.bookmyflight.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.model.AirlineModel
import com.example.bookmyflight.service.repository.FlightDatabase
import com.example.bookmyflight.service.repository.FlightRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepartureViewModel(val repository: FlightRepository,application: Application)
    : BaseViewModel(application) {

    val flightList = MutableLiveData<FlightModel>()
    val flightLoading = MutableLiveData<Boolean>()

    fun refreshData(){

    }

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

    private fun showFlights(flightsList: FlightModel) {
        flightList.value = flightsList
    }

    private fun storeInRoom(list : ArrayList<FlightModel>){
        launch {
            val dao = FlightDatabase(getApplication()).flightDao()
            dao.deleteAllFlight()
            val listLong = dao.insertAll(*list.toTypedArray()) // -> list -> individual
            var i = 0
            while (i<list.size){
                list[i].uuid = listLong[i].toInt()
                i++
            }

            showFlights(list[1])
        }
    }

}