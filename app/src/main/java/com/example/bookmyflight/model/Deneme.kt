package com.example.bookmyflight.model

import java.io.Serializable
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class Flight : Serializable {
    /**
     * {
     * "flights": [
     * {
     * "lastUpdatedAt": "2020-02-11T09:04:21.190Z",
     * "actualLandingTime": "2020-02-11T09:04:21.190Z",
     * "actualOffBlockTime": "2020-02-11T09:04:21.190Z",
     * "aircraftRegistration": "string",
     * "aircraftType": {                                      --> aircraftType
     * "iataMain": "string",
     * "iataSub": "string"
     * },
     * "baggageClaim": {
     * "belts": [
     * "string"
     * ]
     * },
     * "checkinAllocations": {
     * "checkinAllocations": [
     * {
     * "endTime": "2020-02-11T09:04:21.190Z",
     * "rows": {
     * "rows": [
     * {
     * "position": "string",
     * "desks": {
     * "desks": [
     * {
     * "checkinClass": {
     * "code": "string",
     * "description": "string"
     * },
     * "position": 0
     * }
     * ]
     * }
     * }
     * ]
     * },
     * "startTime": "2020-02-11T09:04:21.190Z"
     * }
     * ],
     * "remarks": {
     * "remarks": [
     * "string"
     * ]
     * }
     * },
     * "codeshares": {                                            --> codeshares
     * "codeshares": [
     * "string"
     * ]
     * },
     * "estimatedLandingTime": "2020-02-11T09:04:21.190Z",
     * "expectedTimeBoarding": "2020-02-11T09:04:21.190Z",
     * "expectedTimeGateClosing": "2020-02-11T09:04:21.190Z",
     * "expectedTimeGateOpen": "2020-02-11T09:04:21.190Z",
     * "expectedTimeOnBelt": "2020-02-11T09:04:21.190Z",
     * "expectedSecurityFilter": "string",
     * "flightDirection": "A",
     * "flightName": "string",                                    --> name
     * "flightNumber": 0,
     * "gate": "string",                                          --> gate
     * "pier": "string",
     * "id": "string",                                            --> id
     * "mainFlight": "string",
     * "prefixIATA": "string",
     * "prefixICAO": "string",
     * "airlineCode": 0,
     * "publicEstimatedOffBlockTime": "2020-02-11T09:04:21.190Z", --> estimatedDateTime
     * "publicFlightState": {                                     --> flightStates
     * "flightStates": [
     * "string"
     * ]
     * },
     * "route": {                                                 --> destinations
     * "destinations": [
     * "string"
     * ],
     * "eu": "string",
     * "visa": true
     * },
     * "scheduleDateTime": "2020-02-11T09:04:21.190Z",            --> scheduleDateTime
     * "scheduleDate": "string",                                  --> scheduleDate
     * "scheduleTime": "string",                                  --> scheduleTime
     * "serviceType": "string",
     * "terminal": 0,                                             --> terminal
     * "transferPositions": {
     * "transferPositions": [
     * 0
     * ]
     * },
     * "schemaVersion": "string"
     * }
     * ]
     * }
     */
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