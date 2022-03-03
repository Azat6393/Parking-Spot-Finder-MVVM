package com.azatberdimyradov.parkingspotfinder.domain.model

data class ParkingSpot(
    val lat: Double,
    val lng: Double,
    val id: Int? = null
)
