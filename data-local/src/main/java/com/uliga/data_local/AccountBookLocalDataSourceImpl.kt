package com.uliga.data_local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.uliga.data.datasource.AccountBookLocalDataSource
import javax.inject.Inject

class AccountBookLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): AccountBookLocalDataSource {

    override suspend fun updateCurrentAccountBookName(accountBookName: String) {
        dataStore.edit {
            it[ACCOUNT_BOOK_NAME] = accountBookName
        }
    }

    override suspend fun getCurrentAccountBookName(): String {
        return dataStore.readValue(ACCOUNT_BOOK_NAME, "")
    }

    override suspend fun updateCurrentAccountBookId(accountBookId: Long) {
        dataStore.edit {
            it[ACCOUNT_BOOK_ID] = accountBookId
        }
    }

    override suspend fun getCurrentAccountBookId(): Long {
        return dataStore.readValue(ACCOUNT_BOOK_ID, 0L)
    }

    companion object {
        val ACCOUNT_BOOK_NAME = stringPreferencesKey("accountBookName")
        val ACCOUNT_BOOK_ID = longPreferencesKey("accountBookId")
    }
}