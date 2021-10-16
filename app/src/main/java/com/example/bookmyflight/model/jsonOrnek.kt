package com.example.bookmyflight.model

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