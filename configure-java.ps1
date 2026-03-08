# Java Configuration Helper Script
# This script will find your Java 17 installation and configure gradle.properties

Write-Host "Searching for Java 17 installation..." -ForegroundColor Cyan

# Search common installation locations
$searchPaths = @(
    "C:\Program Files\Amazon Corretto",
    "C:\Program Files\Java",
    "C:\Program Files\Eclipse Adoptium",
    "C:\Program Files\Zulu",
    "C:\Program Files (x86)\Amazon Corretto",
    "C:\Program Files (x86)\Java"
)

$javaHome = $null

foreach ($basePath in $searchPaths) {
    if (Test-Path $basePath) {
        Write-Host "Checking: $basePath" -ForegroundColor Gray
        $jdkDirs = Get-ChildItem -Path $basePath -Directory -ErrorAction SilentlyContinue |
                   Where-Object { $_.Name -match "jdk.*17|jdk-17|17\." }

        if ($jdkDirs) {
            $javaHome = $jdkDirs[0].FullName
            Write-Host "✓ Found Java at: $javaHome" -ForegroundColor Green
            break
        }
    }
}

if (-not $javaHome) {
    Write-Host "❌ Could not automatically find Java 17 installation" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please install Java 17 from:" -ForegroundColor Yellow
    Write-Host "https://corretto.aws/downloads/latest/amazon-corretto-17-x64-windows-jdk.msi"
    Write-Host ""
    Write-Host "Or manually set the path in gradle.properties" -ForegroundColor Yellow
    exit 1
}

# Verify java.exe exists
$javaExe = Join-Path $javaHome "bin\java.exe"
if (-not (Test-Path $javaExe)) {
    Write-Host "❌ java.exe not found at: $javaExe" -ForegroundColor Red
    exit 1
}

# Get Java version
$versionOutput = & $javaExe -version 2>&1
Write-Host ""
Write-Host "Java Version:" -ForegroundColor Cyan
Write-Host $versionOutput -ForegroundColor Gray

# Update gradle.properties
$gradlePropsPath = Join-Path $PSScriptRoot "gradle.properties"
$gradleProps = Get-Content $gradlePropsPath

# Escape backslashes for properties file
$escapedPath = $javaHome -replace '\\', '\\\\'

# Check if already configured
$alreadyConfigured = $gradleProps | Where-Object { $_ -match "^org\.gradle\.java\.home=" }

if ($alreadyConfigured) {
    Write-Host ""
    Write-Host "⚠ gradle.properties already has a java.home setting:" -ForegroundColor Yellow
    Write-Host $alreadyConfigured -ForegroundColor Gray
    Write-Host ""
    $response = Read-Host "Do you want to update it? (y/n)"
    if ($response -ne 'y') {
        Write-Host "Skipping update." -ForegroundColor Yellow
        exit 0
    }
    # Remove old setting
    $gradleProps = $gradleProps | Where-Object { $_ -notmatch "^org\.gradle\.java\.home=" }
}

# Add new setting after the JDK path comment section
$newLine = "org.gradle.java.home=$escapedPath"
$insertIndex = 0

for ($i = 0; $i -lt $gradleProps.Count; $i++) {
    if ($gradleProps[$i] -match "# Specifies the JVM arguments") {
        $insertIndex = $i
        break
    }
}

$newContent = @()
$newContent += $gradleProps[0..($insertIndex - 1)]
$newContent += $newLine
$newContent += ""
$newContent += $gradleProps[$insertIndex..($gradleProps.Count - 1)]

$newContent | Set-Content $gradlePropsPath

Write-Host ""
Write-Host "✓ Successfully configured gradle.properties!" -ForegroundColor Green
Write-Host "  Set: $newLine" -ForegroundColor Cyan
Write-Host ""
Write-Host "Next steps:" -ForegroundColor Yellow
Write-Host "1. Run: .\gradlew assembleDebug" -ForegroundColor White
Write-Host "2. Or open the project in Android Studio" -ForegroundColor White
Write-Host ""

