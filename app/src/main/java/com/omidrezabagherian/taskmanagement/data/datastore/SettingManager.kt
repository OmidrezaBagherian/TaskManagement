package com.omidrezabagherian.taskmanagement.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "APP_DATASTORE")

    private val dataStore = context.dataStore

    companion object {
        val themeKey = stringPreferencesKey("THEME_KEY")
    }

    suspend fun setTheme(isTheme: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[themeKey] = isTheme
        }
    }

    fun getTheme(): Flow<String> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val theme = preferences[themeKey] ?: Theme.DEFAULT.name
            theme
        }


}