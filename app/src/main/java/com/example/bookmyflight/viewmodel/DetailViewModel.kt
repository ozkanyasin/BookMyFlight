package com.example.bookmyflight.viewmodel

import android.graphics.Bitmap
import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookmyflight.databinding.FragmentFlightDetailBinding
import com.example.bookmyflight.mode.Flight
import com.example.bookmyflight.service.repository.FlightRepository
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import retrofit2.Call
import retrofit2.Response

class DetailViewModel(val repository: FlightRepository) : ViewModel() {

    lateinit var binding : FragmentFlightDetailBinding
    val flightLiveData = MutableLiveData<Flight>()

    fun getData(flightUuid: String) {
        val response = repository.getFlight(flightUuid)
        response.enqueue(object : retrofit2.Callback<Flight>{
            override fun onResponse(call: Call<Flight>, response: Response<Flight>) {
                val flight = response.body()
                flightLiveData.postValue(flight)
                if (flight != null) {
                    createQRCode(flight.flightName.toString())
                }

                //bind values
                binding.flightDetailDatetime.text = flight?.scheduleDateTime.toString()
                binding.flightDetailGate.text = flight?.gate ?: "Unknown"
                binding.flightDetailTerminal.text = flight?.terminal.toString()
                binding.flightDetailDestination.text = flight?.route?.destinations.toString()
                binding.flightDetailState.text = flight?.publicFlightState?.flightStates.toString()
                binding.flightDetailAircraft.text = flight?.aircraftType?.iataMain ?: "Unknown"

            }

            override fun onFailure(call: Call<Flight>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    fun createQRCode(data: String){

        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(data,BarcodeFormat.QR_CODE,512,512)
            val width = bitMatrix.width
            val heigh = bitMatrix.height
            val bmp = Bitmap.createBitmap(width,heigh,Bitmap.Config.RGB_565)
            for (x in 0 until width){
                for (y in 0 until heigh){
                    bmp.setPixel(x,y,if (bitMatrix[x,y]) Color.BLACK else Color.WHITE)
                }
            }
            binding.flightQR.setImageBitmap(bmp)
        }catch (e: WriterException){
            e.printStackTrace()
        }
    }

}