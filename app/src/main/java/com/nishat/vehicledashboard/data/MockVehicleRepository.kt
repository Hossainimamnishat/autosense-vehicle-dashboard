package com.nishat.vehicledashboard.data

import com.nishat.vehicledashboard.model.VehicleStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

object MockVehicleRepository {
    fun statusFlow(pollMs: Long = 1000L): Flow<VehicleStatus> = flow {
        var songProgress = 0.45f
        var cabinTemp = 17

        while (true) {
            // Get current time
            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            val currentTime = timeFormat.format(Date())

            // Update song progress
            songProgress += 0.01f
            if (songProgress > 1f) songProgress = 0f

            // Simulate slight variations in tire pressure
            val tirePressureFrontLeft = 32.5f + Random.nextFloat() * 0.3f
            val tirePressureFrontRight = 32.8f + Random.nextFloat() * 0.3f
            val tirePressureRearLeft = 31.5f + Random.nextFloat() * 0.3f
            val tirePressureRearRight = 31.8f + Random.nextFloat() * 0.3f

            emit(
                VehicleStatus(
                    speed = Random.nextInt(0, 120),
                    fuelLevel = 85,
                    batteryLevel = 92,
                    engineTempC = 70 + Random.nextInt(-2, 3),
                    warnings = emptyList(),
                    tirePressureFrontLeft = tirePressureFrontLeft,
                    tirePressureFrontRight = tirePressureFrontRight,
                    tirePressureRearLeft = tirePressureRearLeft,
                    tirePressureRearRight = tirePressureRearRight,
                    outsideTemp = 24,
                    cabinTemp = cabinTemp,
                    weatherCondition = "Sunny",
                    latitude = 23.8103,
                    longitude = 90.4125,
                    currentSong = "Blinding Lights",
                    currentArtist = "The Weeknd",
                    albumArt = "",
                    songProgress = songProgress,
                    driverName = "Nishat",
                    currentTime = currentTime,
                    nearbyChargingStations = 3,
                    isCharging = false
                )
            )
            delay(pollMs)
        }
    }
}

