package dev.ronnie.pokeapiandroidtask.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject


/**
 *created by Ronnie Otieno on 13-Feb-21.
 **/
class DataStoreRepository @Inject constructor(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = "settings"
    )
    private val isDialogShown = booleanPreferencesKey("dialog_shown")

    suspend fun saveDialogShown() {
        dataStore.edit { settings ->
            settings[isDialogShown] = true
        }
    }

    val isDialogShownFlow: Flow<Boolean?> = dataStore.data
        .map { preferences ->
            preferences[isDialogShown]
        }
}