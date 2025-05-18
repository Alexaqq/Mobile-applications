package com.example.lab_5.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport WHERE name LIKE '%' || :query || '%' OR iata_code LIKE '%' || :query || '%' ORDER BY passengers DESC")
    suspend fun searchAirports(query: String): List<AirportEntity>

    @Query("SELECT * FROM airport WHERE iata_code = :code LIMIT 1")
    suspend fun getAirportByCode(code: String): AirportEntity?

    @Query("SELECT * FROM airport ORDER BY passengers DESC")
    suspend fun getAllAirports(): List<AirportEntity>
}
