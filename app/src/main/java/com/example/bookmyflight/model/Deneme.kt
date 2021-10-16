package com.example.bookmyflight.model

import java.io.Serializable
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Flight : Serializable {

    var id: String? = null
    var scheduleDate: String? = null
    var scheduleTime: String? = null
    private lateinit var flightStates: Array<String>
    var name: String? = null
    var destinations = arrayOf<String>()
    var terminal = 0
    var gate: String? = null
    var aircraftType: AircraftType? = null
    var mainFlight: String? = null
    lateinit var codeShares: Array<String>
    var icao: String? = null
    var scheduleDateTime: LocalDateTime? = null
    var estimatedDateTime: LocalDateTime? = null

    fun setFlightStates(flightStates: Array<String>) {
        this.flightStates = flightStates
    }

    fun getFlightStates(): Array<String>? {
        return flightStates
    }

    val firstFlightState: String
        get() = if (getFlightStates()!!.size > 0) {
            getFlightStates()!!.get(0)
        } else {
            "State unknown"
        }

    fun hasState(state: String): Boolean {
        if (getFlightStates() != null && getFlightStates()!!.size > 0) {
            for (s in getFlightStates()!!) {
                if (s == state) {
                    return true
                }
            }
        }
        return false
    }

    val delayedInMinutes: Long
        get() {
            return if (scheduleDateTime == null || estimatedDateTime == null) {
                0
            } else scheduleDateTime!!.until(estimatedDateTime, ChronoUnit.MINUTES)
        }

    class AircraftType : Serializable {
        var iataMain: String? = null
        var iataSub: String? = null
    }
}