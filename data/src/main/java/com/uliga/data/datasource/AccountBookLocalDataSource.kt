package com.uliga.data.datasource

interface AccountBookLocalDataSource {

    suspend fun updateCurrentAccountBookName(accountBookName: String)

    suspend fun getCurrentAccountBookName(): String

    suspend fun updateCurrentAccountBookId(accountBookId: Long)

    suspend fun getCurrentAccountBookId(): Long
}