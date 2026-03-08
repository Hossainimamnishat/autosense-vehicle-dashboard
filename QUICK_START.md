# Vehicle Dashboard - Quick Start Guide

## 🚀 Two Ways to Build This Project

### Option 1: Android Studio (EASIEST - RECOMMENDED) ✅

1. **Open Android Studio**
2. **Click "Open"** and select: `C:\Users\hossa\AndroidStudioProjects\AutoSenseDashboard`
3. **Wait** for Gradle sync to complete
4. **Click Run** (green triangle button)
5. **Done!** The app will build and run automatically

**Android Studio uses its own JDK, so no manual Java configuration needed!**

---

### Option 2: Command Line Build

If Java 17 is installed correctly:

1. **Find your Java 17 installation path**:
   - Run `find-java.bat` in this folder
   - Look for a folder like: `C:\Program Files\Amazon Corretto\jdk17.0.13`

2. **Edit `gradle.properties`**:
   - Open `gradle.properties` in a text editor
   - Find the line starting with `# org.gradle.java.home=`
   - Uncomment it and set your Java path (use double backslashes):
     ```
     org.gradle.java.home=C\\:\\Program Files\\Amazon Corretto\\jdk17.0.13
     ```

3. **Build**:
   ```powershell
   .\gradlew assembleDebug
   ```

---

## ⚠️ Troubleshooting

### "Java 17 not found after installation"

Your Java 17 installation may not have completed successfully. Try:

1. **Verify installation**:
   - Open "Add or Remove Programs" in Windows
   - Search for "Amazon Corretto" or "Java"
   - If not listed, the installation failed

2. **Reinstall**:
   - Download again: https://corretto.aws/downloads/latest/amazon-corretto-17-x64-windows-jdk.msi
   - Run as Administrator
   - Choose default installation path

3. **Or just use Android Studio** (much easier!)

---

## 📱 What This App Does

- Real-time vehicle dashboard with Jetpack Compose
- Shows: Speed, Fuel Level, Engine Temperature
- Displays warnings when thresholds are exceeded
- Uses Material 3 design
- Mock data updates every 2 seconds

---

## 📂 Project Files Created

All files are ready to go:
- ✅ MainActivity.kt
- ✅ VehicleStatus.kt (model)
- ✅ MockVehicleRepository.kt (data layer)
- ✅ VehicleViewModel.kt (ViewModel)
- ✅ DashboardScreen.kt (UI)
- ✅ InfoCard.kt & WarningCard.kt (components)
- ✅ Theme files (Color, Theme, Type)
- ✅ Compose dependencies configured in build.gradle.kts

**Just open in Android Studio and run!** 🎉

