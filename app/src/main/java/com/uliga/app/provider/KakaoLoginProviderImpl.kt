package com.uliga.app.provider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class KakaoLoginProviderImp @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) {
}