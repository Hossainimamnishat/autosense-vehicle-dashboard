# BMW 318i Premium Dashboard - Setup Instructions

## 🚗 Overview
This is a premium automotive dashboard UI for BMW 318i Sedan with real-time Google Maps integration, designed for in-vehicle infotainment systems.

## 📋 Features
- **Premium BMW Dark Theme**: Deep charcoal backgrounds with BMW blue accent lighting
- **Real-time Google Maps**: Live location tracking and navigation
- **Vehicle Visualization**: Top-down 3D view with tire pressure monitoring
- **Climate Control**: Interactive temperature control with slider
- **Media Player**: Now playing display with playback controls
- **Smart Controls**: Quick access grid for vehicle functions
- **Live Data**: Real-time updates for vehicle stats, time, and battery level

## 🔧 Setup Instructions

### 1. Get Google Maps API Key

1. Go to [Google Cloud Console](https://console.cloud.google.com/)
2. Create a new project or select an existing one
3. Enable the **Maps SDK for Android**
4. Go to **Credentials** → **Create Credentials** → **API Key**
5. Copy your API key

### 2. Configure the API Key

Open `app/src/main/AndroidManifest.xml` and replace `YOUR_GOOGLE_MAPS_API_KEY_HERE` with your actual API key:

```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_ACTUAL_API_KEY_HERE" />
```

### 3. Sync and Build

1. Open the project in Android Studio
2. Sync Gradle files (File → Sync Project with Gradle Files)
3. Build the project (Build → Make Project)
4. Run on an emulator or physical device

## 📱 Display Requirements

- **Orientation**: Landscape (forced in AndroidManifest.xml)
- **Recommended Resolution**: 1408×768 (wide in-vehicle screen)
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

## 🎨 Design Highlights

### Color Scheme
- **Background**: Deep charcoal (#0A0A0A)
- **Cards**: Dark gray (#1A1A1A, #252525)
- **Accent**: BMW Blue (#1C86EE, #00A3FF)
- **Status Colors**: Green (#00FF88), Red (#FF4444), Yellow (#FFCC00)

### Layout Structure
```
┌─────────────────────────────────────────────────────────┐
│                      Top Bar                             │
│  Time | Emergency | Driver | Battery                    │
├───────────────┬─────────────────────┬──────────────────┤
│               │                     │                  │
│  Left Panel   │   Center Panel      │  Right Panel     │
│               │                     │                  │
│  • Vehicle    │   • Google Maps     │  • Media Player  │
│    3D View    │   • Climate         │  • Quick         │
│  • Tire       │     Control         │    Controls      │
│    Pressure   │                     │                  │
│  • Charging   │                     │                  │
│                                                         │
├─────────────────────────────────────────────────────────┤
│              Bottom Navigation Bar                      │
│  Menu | Direction | Apps | HOME | Camera | Media | ⚙   │
└─────────────────────────────────────────────────────────┘
```

## 🔄 Real-time Updates

The dashboard updates every 1 second with:
- Current time
- Song progress
- Tire pressure variations
- Vehicle stats

## 📝 Customization

### Change Vehicle Location
Edit `MockVehicleRepository.kt`:
```kotlin
latitude = 23.8103,  // Your latitude
longitude = 90.4125  // Your longitude
```

### Change Driver Name
Edit `MockVehicleRepository.kt`:
```kotlin
driverName = "Your Name"
```

### Modify Update Interval
Edit `MockVehicleRepository.kt`:
```kotlin
fun statusFlow(pollMs: Long = 1000L) // Change from 1000ms (1 second)
```

## 🛠️ Technologies Used

- **Kotlin** - Primary language
- **Jetpack Compose** - Modern UI toolkit
- **Material 3** - Design system
- **Google Maps Compose** - Real-time maps
- **Kotlin Coroutines & Flow** - Reactive data streams
- **ViewModel** - MVVM architecture

## 📦 Dependencies

- androidx.compose.ui:ui:1.5.3
- androidx.compose.material3:material3:1.1.0
- com.google.maps.android:maps-compose:4.3.0
- com.google.android.gms:play-services-maps:18.2.0
- androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2

## 🎯 Future Enhancements

- [ ] Add route navigation with turn-by-turn directions
- [ ] Implement voice commands
- [ ] Add trip statistics
- [ ] Vehicle diagnostics panel
- [ ] Multiple theme options
- [ ] Gesture controls
- [ ] Multiple driver profiles
- [ ] Integration with vehicle OBD-II

## 📄 License

This is a demonstration project showcasing premium automotive UI design.

## 🙋‍♂️ Support

For issues or questions, please check:
1. Google Maps API key is correctly configured
2. Internet permission is granted
3. Location services are enabled
4. Gradle sync completed successfully

---

**Enjoy your premium BMW dashboard experience! 🚗💨**

