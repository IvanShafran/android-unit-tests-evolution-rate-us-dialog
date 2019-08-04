package com.github.ivanshafran.unit_tests_evolution

import android.content.Context

class BuyPreferences(context: Context) {

    companion object {
        private val PREFERENCES_FILENAME = "BUY_PREFERENCES"
        private val BUY_COUNT_KEY = "BUY_COUNT_KEY"
    }

    private val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)

    fun incrementBuyCount() {
        val count = getBuyCount()
        sharedPreferences.edit().putInt(BUY_COUNT_KEY, count + 1).apply()
    }

    fun getBuyCount(): Int {
        return sharedPreferences.getInt(BUY_COUNT_KEY, 0)
    }

}
