package com.uliga.app.base

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uliga.app.view.SchemeActivity
import java.lang.ref.WeakReference

val Activity.isActiveActivity: Boolean
    get() = (
            !this.isChangingConfigurations &&
                    !this.isFinishing &&
                    this.window.decorView.windowToken != null
            )

val Activity.isOwnedActivity: Boolean
    get() = (this is UligaActivity)

internal object LastActivityUtils : BaseUtils() {

    private const val CONST_BACKGROUND_TIME = 3000L

    @JvmStatic
    var _lastActivity: WeakReference<Activity>? = null

    @JvmStatic
    val lastActivity: Activity?
        get() {
            val lastActivity = _lastActivity?.get() ?: return null
            if (!lastActivity.isActiveActivity) {
                return null
            }
            return lastActivity
        }

    @JvmStatic
    var currentTime: Long? = null

    @JvmStatic
    var wasInBackground: Boolean = false

    @JvmStatic
    private val activityLifecycleCallback = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityStarted(activity: Activity) {
            if (!activity.isOwnedActivity) return
            _lastActivity = WeakReference(activity)
            if (currentTime != null && ((System.currentTimeMillis() - currentTime!!) > CONST_BACKGROUND_TIME)) {
                wasInBackground = true
            }
            currentTime = null
        }

        override fun onActivityResumed(activity: Activity) {
        }

        override fun onActivityPaused(activity: Activity) {
            wasInBackground = false
        }

        override fun onActivityStopped(activity: Activity) {
            if (_lastActivity?.get() === activity && _lastActivity?.get() !is SchemeActivity) {
                currentTime = System.currentTimeMillis()
                _lastActivity = null
            }
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityDestroyed(activity: Activity) {
        }
    }

    override fun initialize(context: Context) {
        super.initialize(context)
        val application = (applicationContext as Application)
        application.registerActivityLifecycleCallbacks(activityLifecycleCallback)
    }

    @JvmStatic
    @Throws(IllegalStateException::class)
    fun requireLastActivity(): Activity {
        return lastActivity
            ?: throw IllegalStateException("lastActivity can not be null")
    }

    @JvmStatic
    @Throws(IllegalStateException::class)
    fun requireLastAppCompatActivity(): AppCompatActivity {
        return requireLastActivity() as AppCompatActivity
    }
}
