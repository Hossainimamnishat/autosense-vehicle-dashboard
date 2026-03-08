# Charging Station Finder Feature

## Overview
The BMW AutoSense Dashboard now includes a **Charging Station Finder** feature that helps users locate nearby electric vehicle charging stations and get directions via Google Maps.

## Features

### 1. **Nearby Charging Stations List**
- Displays all charging stations within a 10 km radius
- Shows key information for each station:
  - Station name and address
  - Distance from current location
  - Available chargers (e.g., 3/4 available)
  - Charging speed (Rapid, Fast, Slow)
  - Price per kWh
  - User rating
  - Open/Closed status

### 2. **Interactive Map View**
- Real-time Google Maps integration
- Visual markers for all nearby stations
- Current vehicle location marker
- Route preview when a station is selected
- Zoom and pan controls

### 3. **Station Selection**
- Click any station card to select it
- Selected station is highlighted
- Map automatically zooms to selected station
- Route line drawn from current location to selected station

### 4. **Google Maps Navigation**
- **Navigate button** opens Google Maps with turn-by-turn directions
- Automatically uses Google Maps app if installed
- Falls back to web browser if app is not available
- Direct navigation to the selected charging station

## How to Use

### Accessing the Feature
1. From the main dashboard, locate the **Quick Controls** section
2. Click the **"Charging"** button (battery icon)
3. The Charging Station Finder screen will open

### Finding a Station
1. Browse the list of nearby stations on the left
2. View all stations on the map on the right
3. Click any station card to select it
4. The map will zoom to the selected station
5. A route line will appear from your location to the station

### Getting Directions
1. Select a charging station
2. Review the station details at the bottom of the map
3. Click the **"Navigate"** button
4. Google Maps will open with turn-by-turn directions

### Returning to Dashboard
- Click the **Back** button (arrow icon) at the top left
- You'll return to the main BMW dashboard

## Sample Charging Stations (Mock Data)

The app currently includes 6 mock charging stations in Dhaka, Bangladesh:

1. **BMW EV Charging Hub** - Gulshan 2
   - 2.3 km away
   - 3/4 chargers available
   - Rapid charging, ৳45/kWh
   - Rating: 4.8★

2. **PowerStation Banani** - Banani
   - 3.1 km away
   - 2/6 chargers available
   - Fast charging, ৳38/kWh
   - Rating: 4.5★

3. **Tesla Supercharger Bashundhara** - Bashundhara City
   - 1.8 km away
   - 5/8 chargers available
   - Rapid charging, ৳50/kWh
   - Rating: 4.9★

4. **EV Charge Point Uttara** - Uttara Sector 7
   - 5.7 km away
   - 1/3 chargers available
   - Rapid charging, ৳42/kWh
   - Rating: 4.6★

5. **Quick Charge Dhanmondi** - Dhanmondi 27
   - 7.2 km away
   - 0/4 chargers available
   - Fast charging, ৳40/kWh
   - Rating: 4.2★

6. **EcoCharge Mohakhali** - Mohakhali DOHS
   - 4.5 km away
   - 2/3 chargers available
   - Slow charging, ৳25/kWh
   - Rating: 4.0★ (Currently Closed)

## Technical Implementation

### New Files Added

```
app/src/main/java/com/nishat/vehicledashboard/
├── model/
│   └── ChargingStation.kt           # Data model for charging stations
├── data/
│   └── ChargingStationRepository.kt # Mock data repository
└── ui/
    └── ChargingStationFinderScreen.kt # UI for charging station finder
```

### Updated Files

- **MainActivity.kt**: Added navigation logic between dashboard and charging finder
- **VehicleViewModel.kt**: Added state management for charging stations and navigation
- **DashboardScreen.kt**: Added onClick handler for Charging button

### Key Components

#### 1. ChargingStation Model
```kotlin
data class ChargingStation(
    val id: String,
    val name: String,
    val address: String,
    val location: LatLng,
    val distance: Double,
    val availableChargers: Int,
    val totalChargers: Int,
    val chargingSpeed: String,
    val price: String,
    val isOpen: Boolean,
    val rating: Float
)
```

#### 2. ChargingStationRepository
- Provides mock charging station data
- `getNearbyChargingStations()`: Returns stations within specified radius
- `getAllStations()`: Returns all available stations

#### 3. VehicleViewModel Updates
- `chargingStations`: StateFlow of nearby stations
- `selectedStation`: StateFlow of currently selected station
- `showChargingStationFinder`: Navigation state
- `navigateToChargingStationFinder()`: Opens charging finder
- `navigateBack()`: Returns to dashboard
- `selectChargingStation()`: Selects a station for navigation

## Permissions Required

The following permissions are already configured in AndroidManifest.xml:

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

## Google Maps API Key

**Important**: Make sure to add your Google Maps API key in `AndroidManifest.xml`:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_ACTUAL_API_KEY_HERE" />
```

To get a Google Maps API key:
1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Enable the Maps SDK for Android
4. Create credentials (API Key)
5. Replace `YOUR_GOOGLE_MAPS_API_KEY_HERE` in AndroidManifest.xml

## Future Enhancements

Potential improvements for the charging station finder:

1. **Real-time Data Integration**
   - Connect to actual charging station APIs (e.g., ChargePoint, Tesla Supercharger Network)
   - Real-time availability updates
   - Live pricing information

2. **Filtering Options**
   - Filter by charging speed (Rapid/Fast/Slow)
   - Filter by availability
   - Filter by price range
   - Filter by rating

3. **Advanced Features**
   - Reserve a charging spot
   - Payment integration
   - Charging history
   - Favorite stations
   - Real-time wait times
   - Route planning with charging stops for long trips

4. **User Location**
   - Use actual GPS location instead of mock data
   - Auto-update location while driving
   - Location permission handling

5. **Notifications**
   - Notify when approaching a selected station
   - Alert when chargers become available
   - Low battery warnings with nearby stations

## Testing

To test the feature:

1. Build and run the app on an Android device or emulator
2. Grant location permissions if prompted
3. From the dashboard, tap the "Charging" button
4. Browse and select stations
5. Test the Navigate button (requires Google Maps app)

## Troubleshooting

### Google Maps Not Showing
- Verify your API key is correctly set in AndroidManifest.xml
- Ensure the Maps SDK for Android is enabled in Google Cloud Console
- Check that INTERNET permission is granted

### Navigation Not Working
- Install Google Maps app from Play Store
- Or use a web browser as fallback

### Build Errors
- Run `./gradlew clean build`
- Sync Gradle files
- Ensure all dependencies are properly imported

## Credits

Developed for the BMW 318i AutoSense Dashboard
- Framework: Jetpack Compose
- Maps: Google Maps Compose
- Architecture: MVVM with StateFlow

