package com.nishat.vehicledashboard.model

data class VehicleStatus(
    // Vehicle Stats
    val speed: Int = 0,        // km/h
    val fuelLevel: Int = 85,  // percentage
    val batteryLevel: Int = 92, // percentage
    val engineTempC: Int = 70, // degrees Celsius
    val warnings: List<String> = emptyList(),

    // Tire Pressure
    val tirePressureFrontLeft: Float = 32.5f,
    val tirePressureFrontRight: Float = 32.8f,
    val tirePressureRearLeft: Float = 31.5f,
    val tirePressureRearRight: Float = 31.8f,

    // Climate
    val outsideTemp: Int = 24, // Celsius
    val cabinTemp: Int = 17, // Celsius
    val weatherCondition: String = "Sunny",

    // Location
    val latitude: Double = 48.1351, // Munich, Germany (BMW Headquarters)
    val longitude: Double = 11.5820,

    // Media
    val currentSong: String = "Blinding Lights",
    val currentArtist: String = "The Weeknd",
    val albumArt: String = "",
    val songProgress: Float = 0.45f, // 45%

    // Driver Info
    val driverName: String = "Nishat",
    val currentTime: String = "14:30",

    // Charging
    val nearbyChargingStations: Int = 3,
    val isCharging: Boolean = false
)

