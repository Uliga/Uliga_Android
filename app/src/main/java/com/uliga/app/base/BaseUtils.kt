package com.uliga.app.base

import android.content.Context

internal abstract class BaseUtils {

    protected lateinit var applicationContext: Context

    internal open fun initialize(context: Context) {
        applicationContext = context.applicationContext
    }
}