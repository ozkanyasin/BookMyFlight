package com.example.bookmyflight.viewmodel

import android.app.DatePickerDialog
import android.os.Build
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.adapter.ArrivalListAdapter
import com.example.bookmyflight.databinding.FragmentArrivalBinding
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.model.AirlineModel
import com.example.bookmyflight.service.repository.FlightRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class ArrivalViewModel(val repository: FlightRepository)
    : ViewModel(), DatePickerDialog.OnDateSetListener  {

    val flightListAdapter = ArrivalListAdapter(arrayListOf())
    val flightList = MutableLiveData<FlightModel>()
    val airline = MutableLiveData<AirlineModel>()
    var flightListPage = 1
    var flightListResponse : FlightModel? = null

    val flightLoading = MutableLiveData<Boolean>()

    lateinit var binding: FragmentArrivalBinding

    var day = 0
    var month = 0
    var year = 0
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0

    var scheduleDate = ""
        set(value) {
            field = value
            binding.textView2.text = value
            getFlights()
        }
        get() {
            return field
        }


    fun getAirline(iata: String): String {

        val response = repository.getAirline(iata)
        var deneme = ""

        response.enqueue(object  : Callback<AirlineModel>{
            override fun onResponse(call: Call<AirlineModel>, response: Response<AirlineModel>) {
                airline.postValue(response.body())
                deneme = response.body()?.publicName.toString()
            }

            override fun onFailure(call: Call<AirlineModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
        return deneme
    }

    fun getFlights() {

        val response = repository.getFlightsByDate(scheduleDate,"A")
        response.enqueue(object : Callback<FlightModel>{
            override fun onResponse(call: Call<FlightModel>, response: Response<FlightModel>) {
                val liste = response.body()
                val iata = liste?.flights?.get(3)?.prefixIATA.toString()
                liste?.flights?.get(3)?.airlineName?.publicName = getAirline(iata)
                flightList.postValue(liste)

            }

            override fun onFailure(call: Call<FlightModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }



    fun getCurrentDate() : String {

        val cal = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val deneme = simpleDateFormat.format(cal.time)

        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

        var timeZone = ZoneId.of("Asia/Istanbul")

        val date = year.toString() + "-" + (month+1).toString() + "-" + day.toString()

        return date

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