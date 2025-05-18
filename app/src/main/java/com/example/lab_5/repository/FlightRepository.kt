package com.example.lab_5.repository

import com.example.lab_5.data.AirportDao
import com.example.lab_5.data.FavoriteDao
import com.example.lab_5.data.AirportEntity
import com.example.lab_5.data.FavoriteEntity
import com.example.lab_5.datastore.SearchPreferenceManager
import kotlinx.coroutines.flow.Flow

class FlightRepository(
    private val airportDao: AirportDao,
    private val favoriteDao: FavoriteDao,
    private val prefs: SearchPreferenceManager
) {
    suspend fun searchAirports(query: String): List<AirportEntity> =
        airportDao.searchAirports(query)

    suspend fun getAllAirports(): List<AirportEntity> =
        airportDao.getAllAirports()

    suspend fun getFavorites(): List<FavoriteEntity> =
        favoriteDao.getFavorites()

    suspend fun insertFavorite(dep: String, dest: String) =
        favoriteDao.insertFavorite(FavoriteEntity(departureCode = dep, destinationCode = dest))

    suspend fun deleteFavorite(fav: FavoriteEntity) =
        favoriteDao.deleteFavorite(fav)

    val searchTextFlow: Flow<String?> = prefs.searchTextFlow
    suspend fun saveSearchText(text: String) = prefs.saveSearchText(text)
}
