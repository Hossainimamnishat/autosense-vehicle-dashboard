package com.nishat.vehicledashboard.model

import com.google.android.gms.maps.model.LatLng

data class ChargingStation(
    val id: String,
    val name: String,
    val address: String,
    val location: LatLng,
    val distance: Double, // in kilometers
    val availableChargers: Int,
    val totalChargers: Int,
    val chargingSpeed: String, // "Fast", "Rapid", "Slow"
    val price: String, // per kWh or per hour
    val isOpen: Boolean,
    val rating: Float
)

