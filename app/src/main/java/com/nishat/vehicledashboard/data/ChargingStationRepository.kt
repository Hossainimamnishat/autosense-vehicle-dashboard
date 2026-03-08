package com.nishat.vehicledashboard.data

import com.google.android.gms.maps.model.LatLng
import com.nishat.vehicledashboard.model.ChargingStation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object ChargingStationRepository {

    // Mock charging stations near Munich, Germany (BMW Headquarters region)
    private val mockStations = listOf(
        ChargingStation(
            id = "1",
            name = "BMW Welt Charging Hub",
            address = "Am Olympiapark 1, Munich",
            location = LatLng(48.1775, 11.5560),
            distance = 2.3,
            availableChargers = 6,
            totalChargers = 8,
            chargingSpeed = "Rapid",
            price = "€0.45/kWh",
            isOpen = true,
            rating = 4.9f
        ),
        ChargingStation(
            id = "2",
            name = "EnBW Hypercharger Munich",
            address = "Schwabing, Munich",
            location = LatLng(48.1642, 11.5822),
            distance = 3.1,
            availableChargers = 4,
            totalChargers = 6,
            chargingSpeed = "Rapid",
            price = "€0.48/kWh",
            isOpen = true,
            rating = 4.7f
        ),
        ChargingStation(
            id = "3",
            name = "Tesla Supercharger Munich",
            address = "Arabellastraße, Munich",
            location = LatLng(48.1485, 11.6280),
            distance = 1.8,
            availableChargers = 8,
            totalChargers = 12,
            chargingSpeed = "Rapid",
            price = "€0.52/kWh",
            isOpen = true,
            rating = 4.8f
        ),
        ChargingStation(
            id = "4",
            name = "Ionity Munich Ost",
            address = "A94 Autobahn, Munich East",
            location = LatLng(48.1235, 11.7156),
            distance = 5.7,
            availableChargers = 3,
            totalChargers = 6,
            chargingSpeed = "Rapid",
            price = "€0.69/kWh",
            isOpen = true,
            rating = 4.6f
        ),
        ChargingStation(
            id = "5",
            name = "Stadtwerke München Ladepark",
            address = "Maxvorstadt, Munich",
            location = LatLng(48.1486, 11.5669),
            distance = 4.5,
            availableChargers = 2,
            totalChargers = 4,
            chargingSpeed = "Fast",
            price = "€0.39/kWh",
            isOpen = true,
            rating = 4.5f
        ),
        ChargingStation(
            id = "6",
            name = "Audi Charging Hub Ingolstadt",
            address = "Audi Forum, Ingolstadt",
            location = LatLng(48.7665, 11.4257),
            distance = 7.2,
            availableChargers = 0,
            totalChargers = 4,
            chargingSpeed = "Fast",
            price = "€0.42/kWh",
            isOpen = true,
            rating = 4.3f
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

