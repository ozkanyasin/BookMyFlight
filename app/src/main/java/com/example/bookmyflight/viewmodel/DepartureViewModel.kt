package com.example.bookmyflight.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.databinding.FragmentDepartureBinding
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.service.repository.FlightRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class DepartureViewModel(val repository: FlightRepository)
    : ViewModel(), DatePickerDialog.OnDateSetListener {

    val flightList = MutableLiveData<FlightModel>()
    val flightLoading = MutableLiveData<Boolean>()
    lateinit var binding : FragmentDepartureBinding
    var day = 0
    var month = 0
    var year = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var scheduleDate = ""
        set(value) {
            field = value
            binding.textCurrentDate.text = value
            getFlights()
        }
        get() {
            return field
        }


    fun getFlights(){

        val response = repository.getFlightsByDate(scheduleDate,"D")
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

    fun getCurrentDate() : String {

        val cal = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)



        val date = year.toString() + "-" + (month+1).toString() + "-" + day.toString()
        // date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        return date


        /*  var currentDate : String = DateFormat.getDateInstance().format(cal.time)

          return currentDate*/
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun pickDate(view : View)  {
        view.setOnClickListener {
            val dialog = DatePickerDialog(view.context,this,year,month,day)
            dialog.show()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        savedDay = dayOfMonth
        savedMonth = month+1
        savedYear = year

        var date = savedYear.toString() + "-" + savedMonth.toString() + "-" + savedDay
        if (savedDay<10)
            date = savedYear.toString() + "-" + savedMonth.toString() + "-0" + savedDay
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        scheduleDate = date

    }


}