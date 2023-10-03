package com.uliga.data_local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.uliga.data.datasource.UserAuthLocalDataSource
import javax.inject.Inject

class UserAuthLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): UserAuthLocalDataSource {
    override suspend fun updateToken(accessToken: String) {
        dataStore.edit {
            it[ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun getToken(): String {
        return dataStore.readValue(ACCESS_TOKEN, "")
    }

    override suspend fun updateId(id: Long) {
        dataStore.edit {
            it[ID] = id
        }
    }

    override suspend fun getId(): Long {
        return dataStore.readValue(ID, 0L)
    }


    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("accessToken")
        val ID = longPreferencesKey("id")
    }
}