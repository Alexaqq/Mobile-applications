package com.example.lab_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab_5.data.AppDatabase
import com.example.lab_5.datastore.SearchPreferenceManager
import com.example.lab_5.repository.FlightRepository
import com.example.lab_5.viewmodel.FlightViewModel
import com.example.lab_5.ui.FlightSearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)
        val airportDao = db.airportDao()
        val favoriteDao = db.favoriteDao()
        val prefs = SearchPreferenceManager(this)
        val repo = FlightRepository(airportDao, favoriteDao, prefs)
        val viewModel = FlightViewModel(repo)

        setContent {
            FlightSearchScreen(viewModel)
        }
    }
}
