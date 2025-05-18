package com.example.lab_5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_5.data.AirportEntity
import com.example.lab_5.data.FavoriteEntity
import com.example.lab_5.repository.FlightRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlightViewModel(private val repo: FlightRepository) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _selectedAirport = MutableStateFlow<AirportEntity?>(null)
    val selectedAirport: StateFlow<AirportEntity?> = _selectedAirport.asStateFlow()

    private val _airports = MutableStateFlow<List<AirportEntity>>(emptyList())
    val airports: StateFlow<List<AirportEntity>> = _airports.asStateFlow()

    private val _flights = MutableStateFlow<List<Pair<AirportEntity, AirportEntity>>>(emptyList())
    val flights: StateFlow<List<Pair<AirportEntity, AirportEntity>>> = _flights.asStateFlow()

    private val _favorites = MutableStateFlow<List<FavoriteEntity>>(emptyList())
    val favorites: StateFlow<List<FavoriteEntity>> = _favorites.asStateFlow()

    init {
        // Восстановление поиска из DataStore
        viewModelScope.launch {
            repo.searchTextFlow.collect { text ->
                text?.let { _searchText.value = it }
                loadFavorites()
            }
        }
    }

    fun onSearchChange(newText: String) {
        _searchText.value = newText
        _selectedAirport.value = null
        viewModelScope.launch {
            repo.saveSearchText(newText)
            if (newText.isNotBlank()) {
                _airports.value = repo.searchAirports(newText)
            } else {
                loadFavorites()
            }
        }
    }

    fun selectAirport(airport: AirportEntity) {
        _selectedAirport.value = airport
        viewModelScope.launch {
            val allAirports = repo.getAllAirports().filter { it.iataCode != airport.iataCode }
            _flights.value = allAirports.map { dest -> Pair(airport, dest) }
        }
    }

    fun addOrRemoveFavorite(departureCode: String, destinationCode: String) {
        viewModelScope.launch {
            val currentFavorites = repo.getFavorites()
            val match = currentFavorites.find {
                it.departureCode == departureCode && it.destinationCode == destinationCode
            }
            if (match != null) {
                repo.deleteFavorite(match)
            } else {
                repo.insertFavorite(departureCode, destinationCode)
            }
            loadFavorites()
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repo.getFavorites()
        }
    }
}
