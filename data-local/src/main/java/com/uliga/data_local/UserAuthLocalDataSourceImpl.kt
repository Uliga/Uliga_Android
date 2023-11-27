package com.uliga.data_local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
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

    override suspend fun updateIsLogin(isLogin: Boolean) {
        dataStore.edit {
            it[IS_LOGIN] = isLogin
        }
    }

    override suspend fun getIsLogin(): Boolean {
        return dataStore.readValue(IS_LOGIN, false)
    }


    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("accessToken")
        val ID = longPreferencesKey("id")
        val IS_LOGIN = booleanPreferencesKey("isLogin")
    }
}