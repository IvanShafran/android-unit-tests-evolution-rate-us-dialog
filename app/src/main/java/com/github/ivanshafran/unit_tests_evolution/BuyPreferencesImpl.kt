package com.github.ivanshafran.unit_tests_evolution

import android.content.Context

class BuyPreferencesImpl(context: Context) : BuyPreferences {

    companion object {
        private const val PREFERENCES_FILENAME = "BUY_PREFERENCES"
        private const val BUY_COUNT_KEY = "BUY_COUNT_KEY"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)

    override fun incrementBuyCount() {
        val count = getBuyCount()
        sharedPreferences.edit().putInt(BUY_COUNT_KEY, count + 1).apply()
    }

    override fun getBuyCount(): Int {
        return sharedPreferences.getInt(BUY_COUNT_KEY, 0)
    }

}
