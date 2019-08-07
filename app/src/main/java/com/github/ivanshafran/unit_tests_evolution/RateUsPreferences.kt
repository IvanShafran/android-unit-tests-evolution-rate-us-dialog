package com.github.ivanshafran.unit_tests_evolution

interface RateUsPreferences {

    fun getLastShownTimeMillis(): Long

    fun setLastShownTimeMillis(timeMillis: Long)

    fun isNeverShownAgainClicked(): Boolean

    fun setNeverShownAgainClicked()

    fun isRateNowClicked(): Boolean

    fun setRateNowClickedClicked()

}
