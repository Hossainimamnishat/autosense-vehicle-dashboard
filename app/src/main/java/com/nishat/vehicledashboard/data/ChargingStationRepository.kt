package com.nishat.vehicledashboard.data

import com.google.android.gms.maps.model.LatLng
import com.nishat.vehicledashboard.model.ChargingStation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object ChargingStationRepository {

    // Mock charging stations near Dhaka, Bangladesh
    private val mockStations = listOf(
        ChargingStation(
            id = "1",
            name = "BMW EV Charging Hub",
            address = "Gulshan 2, Dhaka",
            location = LatLng(23.7937, 90.4066),
            distance = 2.3,
            availableChargers = 3,
            totalChargers = 4,
            chargingSpeed = "Rapid",
            price = "৳45/kWh",
            isOpen = true,
            rating = 4.8f
        ),
        ChargingStation(
            id = "2",
            name = "PowerStation Banani",
            address = "Banani, Dhaka",
            location = LatLng(23.7956, 90.4026),
            distance = 3.1,
            availableChargers = 2,
            totalChargers = 6,
            chargingSpeed = "Fast",
            price = "৳38/kWh",
            isOpen = true,
            rating = 4.5f
        ),
        ChargingStation(
            id = "3",
            name = "EV Charge Point Uttara",
            address = "Uttara Sector 7, Dhaka",
            location = LatLng(23.8759, 90.3795),
            distance = 5.7,
            availableChargers = 1,
            totalChargers = 3,
            chargingSpeed = "Rapid",
            price = "৳42/kWh",
            isOpen = true,
            rating = 4.6f
        ),
        ChargingStation(
            id = "4",
            name = "Quick Charge Dhanmondi",
            address = "Dhanmondi 27, Dhaka",
            location = LatLng(23.7465, 90.3765),
            distance = 7.2,
            availableChargers = 0,
            totalChargers = 4,
            chargingSpeed = "Fast",
            price = "৳40/kWh",
            isOpen = true,
            rating = 4.2f
        ),
        ChargingStation(
            id = "5",
            name = "Tesla Supercharger Bashundhara",
            address = "Bashundhara City, Dhaka",
            location = LatLng(23.8103, 90.4292),
            distance = 1.8,
            availableChargers = 5,
            totalChargers = 8,
            chargingSpeed = "Rapid",
            price = "৳50/kWh",
            isOpen = true,
            rating = 4.9f
        ),
        ChargingStation(
            id = "6",
            name = "EcoCharge Mohakhali",
            address = "Mohakhali DOHS, Dhaka",
            location = LatLng(23.7808, 90.4011),
            distance = 4.5,
            availableChargers = 2,
            totalChargers = 3,
            chargingSpeed = "Slow",
            price = "৳25/kWh",
            isOpen = false,
            rating = 4.0f
        )
    )

    fun getNearbyChargingStations(
        currentLocation: LatLng,
        radiusKm: Double = 10.0
    ): Flow<List<ChargingStation>> = flow {
        // Filter stations within radius and sort by distance
        val nearbyStations = mockStations
            .filter { it.distance <= radiusKm }
            .sortedBy { it.distance }
        emit(nearbyStations)
    }

    fun getAllStations(): List<ChargingStation> = mockStations
}

