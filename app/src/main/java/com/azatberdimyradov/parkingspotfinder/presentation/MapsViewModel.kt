package com.azatberdimyradov.parkingspotfinder.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azatberdimyradov.parkingspotfinder.domain.model.ParkingSpot
import com.azatberdimyradov.parkingspotfinder.domain.repository.ParkingSpotRepository
import com.google.android.gms.maps.model.MapStyleOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repo: ParkingSpotRepository
) : ViewModel() {

    var state by mutableStateOf(MapState())

    init {
        viewModelScope.launch {
            repo.getParkingSpots().collectLatest { spots ->
                state = state.copy(
                    parkingSpot = spots
                )
            }
        }
    }

    fun onEvent(event: MapEvent) {
        when (event) {
            is MapEvent.ToggleFalloutMap -> {
                state = state.copy(
                    properties = state.properties.copy(
                        mapStyleOptions = if (state.isFalloutMap) {
                            null
                        } else MapStyleOptions(MapStyle.json)
                    ),
                    isFalloutMap = !state.isFalloutMap
                )
            }
            is MapEvent.OnInfoWindowLongClick -> {
                viewModelScope.launch {
                    repo.deleteParkingSpot(event.spot)
                }
            }
            is MapEvent.OnMapLongClick -> {
                viewModelScope.launch {
                    repo.insertParkingSpot(
                        ParkingSpot(
                            event.latLng.latitude,
                            event.latLng.longitude
                        )
                    )
                }
            }
        }
    }

}