package com.example.lab_3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.lab_3.data.model.Place
import com.example.lab_3.data.repository.PlaceRepository

class CategoryViewModel(private val category: String) : ViewModel() {

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places

    init {
        loadPlaces()
    }

    private fun loadPlaces() {
        _places.value = when (category) {
            "Кофейни" -> PlaceRepository.getCafes()
            "Парки" -> PlaceRepository.getParks()
            "Рестораны" -> PlaceRepository.getRestaurants()
            "Для детей" -> PlaceRepository.getForKids()
            "Торговые центры" -> PlaceRepository.getMalls()
            else -> emptyList()
        }
    }

    companion object {
        fun factory(category: String) = viewModelFactory {
            initializer { CategoryViewModel(category) }
        }
    }
}
