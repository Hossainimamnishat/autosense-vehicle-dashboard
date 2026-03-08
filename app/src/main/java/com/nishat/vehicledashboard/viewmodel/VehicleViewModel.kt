package com.nishat.vehicledashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.nishat.vehicledashboard.data.ChargingStationRepository
import com.nishat.vehicledashboard.data.MockVehicleRepository
import com.nishat.vehicledashboard.model.ChargingStation
import com.nishat.vehicledashboard.model.VehicleStatus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VehicleViewModel : ViewModel() {
    private val repo = MockVehicleRepository
    private val chargingRepo = ChargingStationRepository

    val uiState: StateFlow<VehicleStatus> = repo
        .statusFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), VehicleStatus())

    // Navigation state
    private val _showChargingStationFinder = MutableStateFlow(false)
    val showChargingStationFinder: StateFlow<Boolean> = _showChargingStationFinder.asStateFlow()

    // Charging stations
    private val _chargingStations = MutableStateFlow<List<ChargingStation>>(emptyList())
    val chargingStations: StateFlow<List<ChargingStation>> = _chargingStations.asStateFlow()

    // Selected charging station
    private val _selectedStation = MutableStateFlow<ChargingStation?>(null)
    val selectedStation: StateFlow<ChargingStation?> = _selectedStation.asStateFlow()

    init {
        loadChargingStations()
    }

    private fun loadChargingStations() {
        viewModelScope.launch {
            val currentLocation = LatLng(uiState.value.latitude, uiState.value.longitude)
            chargingRepo.getNearbyChargingStations(currentLocation).collect { stations ->
                _chargingStations.value = stations
            }
        }
    }

    fun navigateToChargingStationFinder() {
        _showChargingStationFinder.value = true
        loadChargingStations() // Refresh stations when opening
    }

    fun navigateBack() {
        _showChargingStationFinder.value = false
        _selectedStation.value = null
    }

    fun selectChargingStation(station: ChargingStation) {
        _selectedStation.value = station
    }
}

