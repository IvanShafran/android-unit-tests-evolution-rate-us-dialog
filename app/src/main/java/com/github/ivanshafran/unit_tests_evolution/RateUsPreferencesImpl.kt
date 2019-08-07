package com.github.ivanshafran.unit_tests_evolution

import android.content.Context

class RateUsPreferencesImpl(context: Context) : RateUsPreferences {

    companion object {
        private val PREFERENCES_FILENAME = "RATE_US_PREFERENCES"
        private val LAST_SHOWN_TIME_KEY = "LAST_SHOWN_TIME_KEY"
        private val NEVER_SHOW_AGAIN_CLICKED = "NEVER_SHOW_AGAIN_CLICKED"
        private val RATE_NOW_CLICKED = "RATE_NOW_CLICKED"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)

    override fun getLastShownTimeMillis(): Long {
        return sharedPreferences.getLong(LAST_SHOWN_TIME_KEY, 0)
    }

    override fun setLastShownTimeMillis(timeMillis: Long) {
        sharedPreferences.edit().putLong(LAST_SHOWN_TIME_KEY, timeMillis).apply()
    }

    override fun isNeverShownAgainClicked(): Boolean {
        return sharedPreferences.getBoolean(NEVER_SHOW_AGAIN_CLICKED, false)
    }

    override fun setNeverShownAgainClicked() {
        sharedPreferences.edit().putBoolean(NEVER_SHOW_AGAIN_CLICKED, true).apply()
    }

    override fun isRateNowClicked(): Boolean {
        return sharedPreferences.getBoolean(RATE_NOW_CLICKED, false)
    }

    override fun setRateNowClickedClicked() {
        sharedPreferences.edit().putBoolean(RATE_NOW_CLICKED, true).apply()
    }

}
