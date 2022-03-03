package com.azatberdimyradov.parkingspotfinder.presentation

import com.azatberdimyradov.parkingspotfinder.domain.model.ParkingSpot
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(),
    val parkingSpot: List<ParkingSpot> = emptyList(),
    val isFalloutMap: Boolean = false
)
