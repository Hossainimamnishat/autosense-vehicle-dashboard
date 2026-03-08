package com.nishat.vehicledashboard.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.nishat.vehicledashboard.model.ChargingStation
import com.nishat.vehicledashboard.ui.theme.*
import com.nishat.vehicledashboard.viewmodel.VehicleViewModel

@Composable
fun ChargingStationFinderScreen(
    viewModel: VehicleViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val status by viewModel.uiState.collectAsState()
    val chargingStations by viewModel.chargingStations.collectAsState()
    val selectedStation by viewModel.selectedStation.collectAsState()

    val currentLocation = LatLng(status.latitude, status.longitude)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BMWDarkBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar with Back Button
            ChargingFinderTopBar(
                onBack = onBack,
                stationCount = chargingStations.size
            )

            // Main Content
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left Section - Charging Stations List
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(chargingStations.size) { index ->
                        val station = chargingStations[index]
                        ChargingStationCard(
                            station = station,
                            isSelected = selectedStation?.id == station.id,
                            onClick = {
                                viewModel.selectChargingStation(station)
                            }
                        )
                    }
                }

                // Right Section - Map with Stations
                DashboardCard(
                    modifier = Modifier.weight(1.5f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Nearby Charging Stations",
                            fontSize = 18.sp,
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
                            ChargingStationsMap(
                                currentLocation = currentLocation,
                                stations = chargingStations,
                                selectedStation = selectedStation
                            )
                        }

                        // Selected Station Details & Navigation
                        selectedStation?.let { station ->
                            SelectedStationDetails(
                                station = station,
                                currentLocation = currentLocation,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ChargingFinderTopBar(
    onBack: () -> Unit,
    stationCount: Int
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
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
            // Back Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(40.dp)
                        .background(BMWBlueAccent, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Column {
                    Text(
                        text = "Charging Stations",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = BMWTextPrimary
                    )
                    Text(
                        text = "$stationCount stations nearby",
                        fontSize = 12.sp,
                        color = BMWTextSecondary
                    )
                }
            }

            // Filter Icon (placeholder)
            Icon(
                imageVector = Icons.Default.ElectricBolt,
                contentDescription = "Electric",
                tint = BMWYellow,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun ChargingStationCard(
    station: ChargingStation,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        color = if (isSelected) BMWCardElevated else BMWCardBackground,
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        shadowElevation = if (isSelected) 8.dp else 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = station.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = BMWTextPrimary
                    )
                    Text(
                        text = station.address,
                        fontSize = 12.sp,
                        color = BMWTextSecondary
                    )
                }

                // Distance Badge
                Surface(
                    color = BMWBlueAccent,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "%.1f km".format(station.distance),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            // Station Info
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Availability
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ElectricCar,
                        contentDescription = "Chargers",
                        tint = if (station.availableChargers > 0) BMWGreen else BMWOrange,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${station.availableChargers}/${station.totalChargers}",
                        fontSize = 12.sp,
                        color = BMWTextPrimary
                    )
                }

                // Speed
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.BatteryChargingFull,
                        contentDescription = "Speed",
                        tint = BMWYellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = station.chargingSpeed,
                        fontSize = 12.sp,
                        color = BMWTextPrimary
                    )
                }

                // Price
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachMoney,
                        contentDescription = "Price",
                        tint = BMWBlueAccent,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = station.price,
                        fontSize = 12.sp,
                        color = BMWTextPrimary
                    )
                }

                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = BMWYellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "%.1f".format(station.rating),
                        fontSize = 12.sp,
                        color = BMWTextPrimary
                    )
                }
            }

            // Status
            Text(
                text = if (station.isOpen) "● Open Now" else "● Closed",
                fontSize = 12.sp,
                color = if (station.isOpen) BMWGreen else BMWRed,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ChargingStationsMap(
    currentLocation: LatLng,
    stations: List<ChargingStation>,
    selectedStation: ChargingStation?
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            selectedStation?.location ?: currentLocation,
            13f
        )
    }

    // Update camera when station is selected
    LaunchedEffect(selectedStation) {
        selectedStation?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it.location, 15f)
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapType = MapType.NORMAL,
            isMyLocationEnabled = false
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = true,
            compassEnabled = true,
            mapToolbarEnabled = false
        )
    ) {
        // Current Location Marker
        Marker(
            state = MarkerState(position = currentLocation),
            title = "Your Location",
            icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        )

        // Charging Station Markers
        stations.forEach { station ->
            Marker(
                state = MarkerState(position = station.location),
                title = station.name,
                snippet = "${station.availableChargers}/${station.totalChargers} available",
                icon = BitmapDescriptorFactory.defaultMarker(
                    if (station.id == selectedStation?.id)
                        BitmapDescriptorFactory.HUE_CYAN
                    else
                        BitmapDescriptorFactory.HUE_GREEN
                )
            )
        }

        // Draw polyline from current location to selected station
        selectedStation?.let { station ->
            Polyline(
                points = listOf(currentLocation, station.location),
                color = BMWBlueAccent,
                width = 8f
            )
        }
    }
}

@Composable
fun SelectedStationDetails(
    station: ChargingStation,
    currentLocation: LatLng,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = BMWCardElevated,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Selected Station",
                    fontSize = 12.sp,
                    color = BMWTextSecondary
                )
                Text(
                    text = station.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BMWTextPrimary
                )
                Text(
                    text = "${station.availableChargers} chargers available • ${station.distance} km away",
                    fontSize = 12.sp,
                    color = BMWTextSecondary
                )
            }

            // Navigate Button
            Button(
                onClick = {
                    // Open Google Maps with directions
                    val uri = Uri.parse(
                        "google.navigation:q=${station.location.latitude},${station.location.longitude}&mode=d"
                    )
                    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                        setPackage("com.google.android.apps.maps")
                    }

                    // Fallback to web if Google Maps is not installed
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        val webUri = Uri.parse(
                            "https://www.google.com/maps/dir/?api=1&destination=${station.location.latitude},${station.location.longitude}"
                        )
                        context.startActivity(Intent(Intent.ACTION_VIEW, webUri))
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = BMWBlueAccent
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.height(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Navigation,
                    contentDescription = "Navigate",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Navigate",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

