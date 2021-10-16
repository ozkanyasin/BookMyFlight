package com.example.bookmyflight.viewmodel

import android.app.DatePickerDialog
import android.os.Build
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.databinding.FragmentArrivalBinding
import com.example.bookmyflight.mode.FlightModel
import com.example.bookmyflight.service.repository.FlightRepository
import com.example.bookmyflight.view.ArrivalFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

class ArrivalViewModel(val repository: FlightRepository)
    : ViewModel(), DatePickerDialog.OnDateSetListener  {

    val flightList = MutableLiveData<FlightModel>()
    var flightListPage = 1
    var flightListResponse : FlightModel? = null

    val flightLoading = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()
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
            getFlightsByDate()
        }
        get() {
            return field
        }


    fun refreshData(){
        getDataFromAPI()
    }

    fun getDataFromAPI(){
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


    fun getFlightsByDate() {

            val response = repository.getFlightsByDate(scheduleDate,"A")
            response.enqueue(object : Callback<FlightModel>{
                override fun onResponse(call: Call<FlightModel>, response: Response<FlightModel>) {
                    flightList.postValue(response.body())
                }

                override fun onFailure(call: Call<FlightModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun deneme() {

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

        val date = savedYear.toString() + "-" + savedMonth.toString() + "-" + savedDay
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        scheduleDate = date

       /* getFlightsByDate()*/

    }


}