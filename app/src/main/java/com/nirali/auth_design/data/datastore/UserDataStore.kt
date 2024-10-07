/**Created By Nirali Pandya
 * Date :2024-10-05
 * Time :9:46 p.m.
 * Project Name :CommonConcepts
 *
 */
package com.nirali.auth_design.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = "user_prefs"
)

class UserDataStore(private val context: Context) {

    private object PreferenceKey {
        val EMAIL = stringPreferencesKey("email")
        val PHONE = stringPreferencesKey("phone")
        val USERNAME = stringPreferencesKey("username")

    }

    val email: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKey.EMAIL]
    }
    val phone: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKey.PHONE]
    }
    val username: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[PreferenceKey.USERNAME]
    }

    suspend fun saveData(email: String, phone: String, username: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKey.EMAIL] = email
            preferences[PreferenceKey.PHONE] = phone
            preferences[PreferenceKey.USERNAME] = username
        }
    }


}