# 🚗 AutoSense BMW 318i Premium Dashboard

A premium automotive dashboard UI for BMW 318i Sedan with real-time Google Maps integration, designed for in-vehicle infotainment systems. Features a modern, futuristic design inspired by BMW iDrive systems with dark luxury theme and blue accent lighting.

![BMW Dashboard](https://img.shields.io/badge/Platform-Android-green.svg)
![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.3-blue.svg)

## ✨ Features

### 🎨 Premium BMW Design
- **Dark Luxury Theme**: Deep charcoal and black backgrounds with subtle shadows
- **BMW Blue Accents**: Signature BMW blue lighting (#1C86EE, #00A3FF)
- **Landscape Orientation**: Optimized for wide in-vehicle screens (1408×768)
- **Immersive Display**: Edge-to-edge content with hidden system bars

### 🗺️ Real-time Google Maps
- Live location tracking and navigation
- Dark map theme matching the dashboard
- Interactive map controls
- Location markers and route visualization

### 🚘 Vehicle Status Monitoring
- **3D Vehicle Visualization**: Top-down view of BMW 318i Sedan
- **Tire Pressure Monitoring**: Real-time PSI for all four tires with color-coded indicators
- **Battery Status**: Live battery level display
- **Charging Info**: Nearby charging stations with navigation

### 🌡️ Climate Control
- Outside weather conditions with icon
- Cabin temperature display (17°C)
- Interactive temperature slider (15°C - 40°C)
- Modern control interface

### 🎵 Media Player
- Now playing display with album art
- Song title and artist information
- Progress bar with real-time updates
- Playback controls (Previous, Play/Pause, Next)

### ⚡ Smart Quick Controls
- Climate control toggle
- Live map quick access
- Flashlight control
- Charging station finder
- Music player
- Direction navigation

### 📊 Live Dashboard
- Real-time clock updates
- Emergency call button
- Driver profile display
- Battery percentage
- Auto-updating vehicle stats

## 🏗️ Architecture

Built using modern Android development practices:

- **MVVM Architecture**: Clean separation of concerns
- **Jetpack Compose**: Modern declarative UI
- **Kotlin Coroutines & Flow**: Reactive data streams
- **Material 3**: Latest design system
- **Google Maps Compose**: Native maps integration

## 📋 Project Structure

```
app/src/main/java/com/nishat/vehicledashboard/
├── MainActivity.kt                 # Main entry point
├── model/
│   └── VehicleStatus.kt           # Data model
├── data/
│   └── MockVehicleRepository.kt   # Mock data source
├── viewmodel/
│   └── VehicleViewModel.kt        # ViewModel layer
├── ui/
│   ├── DashboardScreen.kt         # Main dashboard UI
│   └── components/
│       ├── InfoCard.kt            # Reusable info card
│       └── WarningCard.kt         # Warning display
└── ui/theme/
    ├── Color.kt                   # BMW color scheme
    ├── Theme.kt                   # App theme
    └── Type.kt                    # Typography
```

## 🚀 Getting Started

### Prerequisites

- **Android Studio**: Latest version (Hedgehog or newer)
- **Java 17**: Required for Gradle 8.13
- **Google Maps API Key**: For maps integration
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Hossainimamnishat/autosense-vehicle-dashboard.git
   cd autosense-vehicle-dashboard
   ```

2. **Get Google Maps API Key**
   - Go to [Google Cloud Console](https://console.cloud.google.com/)
   - Create a new project
   - Enable **Maps SDK for Android**
   - Create credentials → API Key
   - Copy your API key

3. **Configure API Key**
   
   Open `app/src/main/AndroidManifest.xml` and replace:
   ```xml
   <meta-data
       android:name="com.google.android.geo.API_KEY"
       android:value="YOUR_GOOGLE_MAPS_API_KEY_HERE" />
   ```

4. **Open in Android Studio**
   - Open Android Studio
   - Select "Open" and choose the project folder
   - Wait for Gradle sync to complete

5. **Run the App**
   - Click the Run button (green triangle)
   - Select an emulator or physical device
   - The app will build and launch

### Java 17 Setup (If Needed)

If you encounter Java version issues:

1. **Download Java 17**:
   - [Amazon Corretto 17](https://corretto.aws/downloads/latest/amazon-corretto-17-x64-windows-jdk.msi) (Recommended)

2. **Configure Gradle**:
   
   Add to `gradle.properties`:
   ```properties
   org.gradle.java.home=C\:\\Program Files\\Amazon Corretto\\jdk17.0.x
   ```

3. **Or use Android Studio's JDK** (Easiest):
   
   Android Studio automatically uses its embedded JDK 17 - no configuration needed!

## 🎨 Design Highlights

### Color Palette
```kotlin
BMWDarkBackground = #0A0A0A      // Deep black
BMWCardBackground = #1A1A1A      // Dark charcoal
BMWCardElevated = #252525        // Elevated cards
BMWBlueAccent = #1C86EE          // Primary blue
BMWBlueGlow = #00A3FF            // Glowing blue
BMWGreen = #00FF88               // Success/OK
BMWRed = #FF4444                 // Alerts/Errors
BMWYellow = #FFCC00              // Warnings
```

### Layout Structure
```
┌─────────────────────────────────────────────────────────┐
│                 Top Bar (Time, Driver, Battery)         │
├───────────────┬─────────────────────┬──────────────────┤
│               │                     │                  │
│  Left Panel   │   Center Panel      │  Right Panel     │
│               │                     │                  │
│  • Vehicle    │   • Google Maps     │  • Media Player  │
│    3D View    │     (Real-time)     │  • Song Info     │
│  • Tire       │   • Climate         │  • Playback      │
│    Pressure   │     Control         │    Controls      │
│  • Charging   │   • Temperature     │  • Quick         │
│    Stations   │     Slider          │    Controls      │
│                                                         │
├─────────────────────────────────────────────────────────┤
│       Bottom Nav (Menu|Direction|Apps|HOME|Camera...)   │
└─────────────────────────────────────────────────────────┘
```

## 🔄 Real-time Features

The dashboard updates every second with:
- ⏰ Current time
- 🎵 Song progress
- 🚗 Tire pressure variations
- 🔋 Battery status
- 📍 Location data

## 🛠️ Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Kotlin | 1.9.0 | Programming language |
| Jetpack Compose | 1.5.3 | UI framework |
| Material 3 | 1.1.0 | Design system |
| Google Maps Compose | 4.3.0 | Maps integration |
| Play Services Maps | 18.2.0 | Maps SDK |
| Coroutines & Flow | Latest | Async operations |
| ViewModel | 2.6.2 | MVVM architecture |

## 📱 Customization

### Change Vehicle Location
Edit `MockVehicleRepository.kt`:
```kotlin
latitude = 23.8103,  // Your latitude
longitude = 90.4125  // Your longitude
```

### Update Driver Name
```kotlin
driverName = "Your Name"
```

### Adjust Update Interval
```kotlin
fun statusFlow(pollMs: Long = 1000L) // milliseconds
```

### Modify Theme Colors
Edit `ui/theme/Color.kt` to customize the color scheme.

## 🎯 Future Roadmap

- [ ] Turn-by-turn navigation
- [ ] Voice command integration
- [ ] Trip statistics and analytics
- [ ] Vehicle diagnostics (OBD-II)
- [ ] Multiple driver profiles
- [ ] Gesture controls
- [ ] Theme customization options
- [ ] CarPlay/Android Auto integration

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Hossain Imam Nishat**

- GitHub: [@Hossainimamnishat](https://github.com/Hossainimamnishat)
- Repository: [autosense-vehicle-dashboard](https://github.com/Hossainimamnishat/autosense-vehicle-dashboard)

## 🙏 Acknowledgments

- BMW for design inspiration
- Google Maps Platform for mapping services
- Jetpack Compose community for excellent resources
- Material Design for the design system

## 📞 Support

For issues, questions, or contributions:

1. Check existing [Issues](https://github.com/Hossainimamnishat/autosense-vehicle-dashboard/issues)
2. Create a new issue with detailed description
3. Submit Pull Requests for improvements

## 🌟 Show Your Support

If you like this project, please ⭐ star the repository!

---

**Made with ❤️ for automotive enthusiasts**

*Experience the future of in-vehicle infotainment systems*


