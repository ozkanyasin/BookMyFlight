package com.example.bookmyflight.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.mode.Flight
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.service.repository.FlightRepository
import com.google.gson.internal.bind.util.ISO8601Utils.format
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ArrivalViewModel(val repository: FlightRepository) : ViewModel(), DatePickerDialog.OnDateSetListener  {

    val flightList = MutableLiveData<FlightModel>()
    val flightLoading = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()

    val calender = Calendar.getInstance()
    var day = 0
    var month = 0
    var year = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    var scheduleDate = ""

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){
        disposable.add(
            repository.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<FlightModel>(){
                    override fun onNext(t: FlightModel) {
                       if (t == FlightModel()){
                           dispose()
                       }
                        flightList.postValue(t)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                        flightList.value
                    }


                })
        )
    }

    fun getFlights(){
        val response = repository.getFlightsByDirection("A")
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

    fun getFlightsByDate() {
        val response = repository.getFlightsByDate("2021-10-15","A")
        response.enqueue(object : Callback<FlightModel>{
            override fun onResponse(call: Call<FlightModel>, response: Response<FlightModel>) {
                flightList.postValue(response.body())
            }

            override fun onFailure(call: Call<FlightModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getDate() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

        /*val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)*/

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun pickDate(view : View) {
        view.setOnClickListener {
            getDate()
            val dialog = DatePickerDialog(view.context,this,year,month,day)
            dialog.show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        savedDay = dayOfMonth
        savedMonth = month+1
        savedYear = year

        /*calender.set(Calendar.YEAR,year)
        calender.set(Calendar.MONTH,month)
        calender.set(Calendar.DAY_OF_MONTH,dayOfMonth)

        val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calender.time)*/

        val date = savedYear.toString() + "-" + savedMonth.toString() + "-" + savedDay
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        scheduleDate = date
    }



}