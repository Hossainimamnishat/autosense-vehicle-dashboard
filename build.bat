@echo off
echo ========================================
echo Building Vehicle Dashboard App
echo ========================================
echo.

REM Try to find Java 17 Corretto installation
set JAVA_HOME=
if exist "C:\Program Files\Amazon Corretto\jdk17.0.18" set JAVA_HOME=C:\Program Files\Amazon Corretto\jdk17.0.18
if exist "C:\Program Files\Amazon Corretto\jdk17.0.13" set JAVA_HOME=C:\Program Files\Amazon Corretto\jdk17.0.13
if exist "C:\Program Files\Java\jdk-17" set JAVA_HOME=C:\Program Files\Java\jdk-17

if "%JAVA_HOME%"=="" (
    echo WARNING: Could not find Java 17 Corretto installation.
    echo Gradle will use the Java from your system PATH.
    echo.
) else (
    echo Found Java at: %JAVA_HOME%
    echo.
)

echo Running Gradle build...
echo.
gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================
    echo BUILD SUCCESSFUL! ✓
    echo ========================================
    echo.
    echo APK Location:
    echo app\build\outputs\apk\debug\app-debug.apk
    echo.
) else (
    echo.
    echo ========================================
    echo BUILD FAILED
    echo ========================================
    echo.
    echo Try opening the project in Android Studio instead.
    echo.
)

pause

