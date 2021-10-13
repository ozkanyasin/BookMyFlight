package com.example.bookmyflight.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class AirlineModel {
    @SerializedName("airlines")
    @Expose
    var airlines: List<Airline>? = null
}


class Airline {
    var iata: String? = null
    var icao: String? = null
    var nvls = 0
    @SerializedName("publicName")
    @Expose
    var publicName: String? = null
}

