package com.example.food.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "food_prefs")

@Singleton
class KeypassStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val keypassKey = stringPreferencesKey("keypass")

    val keypassFlow: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[keypassKey] ?: ""
    }

    suspend fun save(keypass: String) {
        context.dataStore.edit { preferences ->
            preferences[keypassKey] = keypass
        }
    }
}

