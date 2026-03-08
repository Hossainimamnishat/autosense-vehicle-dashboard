package com.nishat.vehicledashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nishat.vehicledashboard.data.MockVehicleRepository
import com.nishat.vehicledashboard.model.VehicleStatus
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class VehicleViewModel : ViewModel() {
    private val repo = MockVehicleRepository

    val uiState: StateFlow<VehicleStatus> = repo
        .statusFlow()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), VehicleStatus())
}

