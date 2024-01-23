package br.com.dictionmaster.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")

object PreferencesKey{
    val USER_QUANTITY_SEARCHS = intPreferencesKey("attempts")
}