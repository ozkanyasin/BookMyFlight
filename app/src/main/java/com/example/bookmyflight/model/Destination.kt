package com.example.bookmyflight.model


class DestinationModel {
    var destinations: List<Destination>? = null
}

class PublicName {
    var dutch: String? = null
    var english: String? = null
}

class Destination {
    var city: String? = null
    var country: String? = null
    var iata: String? = null
    var publicName: PublicName? = null
}

