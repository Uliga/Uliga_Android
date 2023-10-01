package com.uliga.app.base

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.uliga.app.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(
            this,
            this.getString(R.string.kakao_native_app_key)
        )
    }
}