package com.example.lab_5.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.lab_5.data.AirportEntity
import com.example.lab_5.data.FavoriteEntity
import com.example.lab_5.viewmodel.FlightViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightSearchScreen(viewModel: FlightViewModel) {
    val searchText by viewModel.searchText.collectAsState()
    val airports by viewModel.airports.collectAsState()
    val selectedAirport by viewModel.selectedAirport.collectAsState()
    val flights by viewModel.flights.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Flight Search") }
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { viewModel.onSearchChange(it) },
            leadingIcon = { Icon(Icons.Filled.Search, null) },
            label = { Text("Enter departure airport") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        when {
            searchText.isBlank() -> {
                Text(
                    "Favorite routes",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(favorites) { fav ->
                        FavoriteRouteCard(fav) {
                            viewModel.addOrRemoveFavorite(fav.departureCode, fav.destinationCode)
                        }
                    }
                }
            }
            selectedAirport == null -> {
                LazyColumn {
                    items(airports) { airport ->
                        AirportSuggestionItem(airport) {
                            viewModel.selectAirport(airport)
                        }
                    }
                }
            }
            selectedAirport != null -> {
                Text(
                    "Flights from ${selectedAirport!!.iataCode}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(8.dp)
                )
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(flights) { (depart, arrive) ->
                        FlightRouteCard(
                            depart = depart,
                            arrive = arrive,
                            isFavorite = favorites.any { it.departureCode == depart.iataCode && it.destinationCode == arrive.iataCode },
                            onStarClick = {
                                viewModel.addOrRemoveFavorite(depart.iataCode, arrive.iataCode)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AirportSuggestionItem(airport: AirportEntity, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp)
    ) {
        Text(airport.iataCode, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.width(48.dp))
        Spacer(Modifier.width(8.dp))
        Text(airport.name, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun FlightRouteCard(
    depart: AirportEntity,
    arrive: AirportEntity,
    isFavorite: Boolean,
    onStarClick: () -> Unit
) {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text("DEPART", style = MaterialTheme.typography.labelSmall)
                Text(
                    "${depart.iataCode}  ${depart.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.height(4.dp))
                Text("ARRIVE", style = MaterialTheme.typography.labelSmall)
                Text(
                    "${arrive.iataCode}  ${arrive.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = onStarClick) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color(0xFFE48E00) else Color.Gray
                )
            }
        }
    }
}

@Composable
fun FavoriteRouteCard(
    fav: FavoriteEntity,
    onRemove: () -> Unit
) {
    Card(
        Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8EAF6))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text("DEPART", style = MaterialTheme.typography.labelSmall)
                Text(fav.departureCode, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(4.dp))
                Text("ARRIVE", style = MaterialTheme.typography.labelSmall)
                Text(fav.destinationCode, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = onRemove) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Remove from favorite",
                    tint = Color(0xFFE48E00)
                )
            }
        }
    }
}

