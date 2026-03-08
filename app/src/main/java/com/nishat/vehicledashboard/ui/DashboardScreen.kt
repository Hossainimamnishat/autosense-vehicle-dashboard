package com.nishat.vehicledashboard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.nishat.vehicledashboard.ui.theme.*
import com.nishat.vehicledashboard.viewmodel.VehicleViewModel

@Composable
fun DashboardScreen(
    viewModel: VehicleViewModel,
    modifier: Modifier = Modifier
) {
    val status by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BMWDarkBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar
            TopBar(
                currentTime = status.currentTime,
                driverName = status.driverName,
                batteryLevel = status.batteryLevel
            )

            // Main Dashboard Content
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left Section - Vehicle Visualization
                LeftSection(
                    modifier = Modifier.weight(1f),
                    tirePressureFrontLeft = status.tirePressureFrontLeft,
                    tirePressureFrontRight = status.tirePressureFrontRight,
                    tirePressureRearLeft = status.tirePressureRearLeft,
                    tirePressureRearRight = status.tirePressureRearRight,
                    nearbyChargingStations = status.nearbyChargingStations
                )

                // Center Section - Map & Climate
                CenterSection(
                    modifier = Modifier.weight(1.5f),
                    latitude = status.latitude,
                    longitude = status.longitude,
                    outsideTemp = status.outsideTemp,
                    cabinTemp = status.cabinTemp,
                    onTempChange = { /* TODO */ }
                )

                // Right Section - Media & Controls
                RightSection(
                    modifier = Modifier.weight(1f),
                    songTitle = status.currentSong,
                    artistName = status.currentArtist,
                    songProgress = status.songProgress,
                    viewModel = viewModel
                )
            }

            // Bottom Navigation Bar
            BottomNavigationBar()
        }
    }
}

@Composable
fun TopBar(
    currentTime: String,
    driverName: String,
    batteryLevel: Int
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        color = BMWCardBackground,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Time
            Text(
                text = currentTime,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = BMWTextPrimary
            )

            // Emergency Call
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Emergency",
                    tint = BMWRed,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Emergency",
                    fontSize = 14.sp,
                    color = BMWRed
                )
            }

            // Driver Profile
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Driver",
                    tint = BMWBlueAccent,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = driverName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = BMWTextPrimary
                )
            }

            // Battery Status
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Battery6Bar,
                    contentDescription = "Battery",
                    tint = BMWGreen,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = "$batteryLevel%",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BMWGreen
                )
            }
        }
    }
}

@Composable
fun LeftSection(
    modifier: Modifier = Modifier,
    tirePressureFrontLeft: Float,
    tirePressureFrontRight: Float,
    tirePressureRearLeft: Float,
    tirePressureRearRight: Float,
    nearbyChargingStations: Int
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Vehicle 3D Visualization Card
        DashboardCard(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "BMW 318i Sedan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BMWTextPrimary
                )

                // Vehicle Top-Down View with Tire Pressure
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    VehicleTopDownView(
                        tirePressureFrontLeft = tirePressureFrontLeft,
                        tirePressureFrontRight = tirePressureFrontRight,
                        tirePressureRearLeft = tirePressureRearLeft,
                        tirePressureRearRight = tirePressureRearRight
                    )
                }
            }
        }

        // Tire Pressure Card
        DashboardCard(
            modifier = Modifier.height(100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Tire Pressure",
                        fontSize = 14.sp,
                        color = BMWTextSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    val avgPressure = (tirePressureFrontLeft + tirePressureFrontRight +
                                      tirePressureRearLeft + tirePressureRearRight) / 4
                    Text(
                        text = "%.1f PSI".format(avgPressure),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = BMWTextPrimary
                    )
                }
                Icon(
                    imageVector = Icons.Default.RadioButtonChecked,
                    contentDescription = "Tire",
                    tint = BMWGreen,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

        // Charging Stations Card
        DashboardCard(
            modifier = Modifier.height(100.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Nearby Charging",
                        fontSize = 14.sp,
                        color = BMWTextSecondary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$nearbyChargingStations Stations",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = BMWTextPrimary
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(40.dp)
                        .background(BMWBlueAccent, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.NearMe,
                        contentDescription = "Navigate",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CenterSection(
    modifier: Modifier = Modifier,
    latitude: Double,
    longitude: Double,
    outsideTemp: Int,
    cabinTemp: Int,
    onTempChange: (Int) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Location Live - Google Maps
        DashboardCard(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Location Live",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BMWTextPrimary,
                    modifier = Modifier.padding(16.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    GoogleMapView(
                        latitude = latitude,
                        longitude = longitude
                    )
                }
            }
        }

        // Climate Control Card
        DashboardCard(
            modifier = Modifier.height(180.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Climate Control",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BMWTextPrimary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Outside Weather
                    Column {
                        Text(
                            text = "Outside",
                            fontSize = 12.sp,
                            color = BMWTextSecondary
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.LightMode,
                                contentDescription = "Weather",
                                tint = BMWYellow,
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "$outsideTemp°C",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = BMWTextPrimary
                            )
                        }
                    }

                    // Cabin Temperature
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Cabin",
                            fontSize = 12.sp,
                            color = BMWTextSecondary
                        )
                        Text(
                            text = "$cabinTemp°C",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = BMWBlueAccent
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Temperature Slider
                var sliderValue by remember { mutableFloatStateOf(cabinTemp.toFloat()) }
                Column {
                    Slider(
                        value = sliderValue,
                        onValueChange = {
                            sliderValue = it
                            onTempChange(it.toInt())
                        },
                        valueRange = 15f..40f,
                        colors = SliderDefaults.colors(
                            thumbColor = BMWBlueAccent,
                            activeTrackColor = BMWBlueAccent,
                            inactiveTrackColor = BMWDivider
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("15°C", fontSize = 10.sp, color = BMWTextTertiary)
                        Text("40°C", fontSize = 10.sp, color = BMWTextTertiary)
                    }
                }
            }
        }
    }
}

@Composable
fun RightSection(
    modifier: Modifier = Modifier,
    songTitle: String,
    artistName: String,
    songProgress: Float,
    viewModel: VehicleViewModel
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Media Player Card
        DashboardCard(
            modifier = Modifier.weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Now Playing",
                    fontSize = 14.sp,
                    color = BMWTextSecondary
                )

                // Album Art Placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(BMWBlueAccent, BMWCardElevated)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.LibraryMusic,
                        contentDescription = "Album",
                        tint = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.size(60.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Song Info
                Column {
                    Text(
                        text = songTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BMWTextPrimary
                    )
                    Text(
                        text = artistName,
                        fontSize = 14.sp,
                        color = BMWTextSecondary
                    )
                }

                // Progress Bar
                LinearProgressIndicator(
                    progress = songProgress,
                    modifier = Modifier.fillMaxWidth(),
                    color = BMWBlueAccent,
                    trackColor = BMWDivider
                )

                // Playback Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Previous",
                            tint = BMWTextPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(48.dp)
                            .background(BMWBlueAccent, CircleShape)
                    ) {
                        Icon(
                            Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            contentDescription = "Next",
                            tint = BMWTextPrimary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
        }

        // Smart Controls Grid
        SmartControlsGrid(viewModel = viewModel)
    }
}

@Composable
fun SmartControlsGrid(viewModel: VehicleViewModel) {
    DashboardCard(
        modifier = Modifier.height(200.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Quick Controls",
                fontSize = 14.sp,
                color = BMWTextSecondary,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ControlButton(Icons.Default.Thermostat, "Climate", Modifier.weight(1f)) { }
                    ControlButton(Icons.Default.Place, "Live Map", Modifier.weight(1f)) { }
                    ControlButton(Icons.Default.Star, "Light", Modifier.weight(1f)) { }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ControlButton(
                        Icons.Default.Battery6Bar,
                        "Charging",
                        Modifier.weight(1f)
                    ) {
                        viewModel.navigateToChargingStationFinder()
                    }
                    ControlButton(Icons.Default.LibraryMusic, "Music", Modifier.weight(1f)) { }
                    ControlButton(Icons.Default.NearMe, "Direction", Modifier.weight(1f)) { }
                }
            }
        }
    }
}

@Composable
fun ControlButton(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.height(60.dp),
        color = BMWCardElevated,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = BMWBlueAccent,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                fontSize = 10.sp,
                color = BMWTextSecondary
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        color = BMWCardBackground,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(Icons.Default.Menu, "Menu", false)
            NavItem(Icons.Default.NearMe, "Direction", false)
            NavItem(Icons.Default.GridView, "Apps", false)
            NavItem(Icons.Default.Home, "Home", true) // Highlighted
            NavItem(Icons.Default.Visibility, "Camera", false)
            NavItem(Icons.Default.LibraryMusic, "Media", false)
            NavItem(Icons.Default.Settings, "Settings", false)
        }
    }
}

@Composable
fun NavItem(icon: ImageVector, label: String, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) BMWBlueAccent else BMWTextTertiary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            color = if (isSelected) BMWBlueAccent else BMWTextTertiary
        )
    }
}

@Composable
fun DashboardCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                spotColor = Color.Black.copy(alpha = 0.5f)
            ),
        color = BMWCardBackground,
        shape = RoundedCornerShape(16.dp)
    ) {
        content()
    }
}

@Composable
fun GoogleMapView(
    latitude: Double,
    longitude: Double
) {
    val position = LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        this.position = CameraPosition.fromLatLngZoom(position, 14f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapType = MapType.NORMAL,
            isMyLocationEnabled = false
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = false,
            mapToolbarEnabled = false
        )
    ) {
        Marker(
            state = MarkerState(position = position),
            title = "Current Location"
        )
    }
}

@Composable
fun VehicleTopDownView(
    tirePressureFrontLeft: Float,
    tirePressureFrontRight: Float,
    tirePressureRearLeft: Float,
    tirePressureRearRight: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.6f),
        contentAlignment = Alignment.Center
    ) {
        // Vehicle Body
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .fillMaxHeight(0.8f)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            BMWCardElevated,
                            BMWCardBackground
                        )
                    )
                )
        )

        // Tire Pressure Indicators
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Front Tires
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TirePressureIndicator(tirePressureFrontLeft, "FL")
                TirePressureIndicator(tirePressureFrontRight, "FR")
            }

            // Rear Tires
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TirePressureIndicator(tirePressureRearLeft, "RL")
                TirePressureIndicator(tirePressureRearRight, "RR")
            }
        }
    }
}

@Composable
fun TirePressureIndicator(pressure: Float, position: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(40.dp, 60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    if (pressure > 30) BMWGreen else BMWOrange
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = position,
                    fontSize = 8.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "%.1f".format(pressure),
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

