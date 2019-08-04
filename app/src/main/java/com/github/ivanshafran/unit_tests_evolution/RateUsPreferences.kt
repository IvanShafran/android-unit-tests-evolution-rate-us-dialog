package com.github.ivanshafran.unit_tests_evolution

import android.content.Context

class RateUsPreferences(context: Context) {

    companion object {
        private val PREFERENCES_FILENAME = "RATE_US_PREFERENCES"
        private val LAST_SHOWN_TIME_KEY = "LAST_SHOWN_TIME_KEY"
        private val NEVER_SHOW_AGAIN_CLICKED = "NEVER_SHOW_AGAIN_CLICKED"
        private val RATE_NOW_CLICKED = "RATE_NOW_CLICKED"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)

    fun getLastShownTimeMillis(): Long {
        return sharedPreferences.getLong(LAST_SHOWN_TIME_KEY, 0)
    }

    fun setLastShownTimeMillis(timeMillis: Long) {
        sharedPreferences.edit().putLong(LAST_SHOWN_TIME_KEY, timeMillis).apply()
    }

    fun isNeverShownAgainClicked(): Boolean {
        return sharedPreferences.getBoolean(NEVER_SHOW_AGAIN_CLICKED, false)
    }

    fun setNeverShownAgainClicked() {
        sharedPreferences.edit().putBoolean(NEVER_SHOW_AGAIN_CLICKED, true).apply()
    }

    fun isRateNowClicked(): Boolean {
        return sharedPreferences.getBoolean(RATE_NOW_CLICKED, false)
    }

    fun setRateNowClickedClicked() {
        sharedPreferences.edit().putBoolean(RATE_NOW_CLICKED, true).apply()
    }

}
