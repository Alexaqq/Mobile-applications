package com.example.lab_5.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "search_prefs")

class SearchPreferenceManager(private val context: Context) {
    companion object {
        val SEARCH_KEY = stringPreferencesKey("search_key")
    }

    val searchTextFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[SEARCH_KEY] }

    suspend fun saveSearchText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[SEARCH_KEY] = text
        }
    }
}

