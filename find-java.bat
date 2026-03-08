@echo off
echo ========================================
echo Java 17 Path Finder
echo ========================================
echo.

echo Searching for Java installations...
echo.

dir "C:\Program Files\Amazon Corretto" 2>nul
if %ERRORLEVEL% EQU 0 (
    echo Found: C:\Program Files\Amazon Corretto
    dir "C:\Program Files\Amazon Corretto" /B
)

dir "C:\Program Files\Java" 2>nul
if %ERRORLEVEL% EQU 0 (
    echo Found: C:\Program Files\Java
    dir "C:\Program Files\Java" /B
)

echo.
echo ========================================
echo.
echo If you see a folder like "jdk-17.x.x" or "jdk17.x.x" above:
echo 1. Copy the full path (e.g., C:\Program Files\Amazon Corretto\jdk17.0.13)
echo 2. Open gradle.properties in a text editor
echo 3. Add this line (replace PATH with your actual path):
echo    org.gradle.java.home=PATH
echo    (Use double backslashes: C:\\Program Files\\...)
echo.
echo ========================================
echo EASIER OPTION:
echo Just open this project in Android Studio!
echo It will use its own JDK automatically.
echo ========================================
pause

