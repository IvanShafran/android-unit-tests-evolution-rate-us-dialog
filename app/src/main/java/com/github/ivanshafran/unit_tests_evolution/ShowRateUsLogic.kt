package com.github.ivanshafran.unit_tests_evolution

import java.util.concurrent.TimeUnit

class ShowRateUsLogic(
    private val rateUsPreferences: RateUsPreferences,
    private val buyPreferences: BuyPreferences,
    private val time: Time
) {

    companion object {
        private val TWO_MONTHS_IN_MILLS = TimeUnit.DAYS.toMillis(60)
    }

    fun shouldShowRateUs(): Boolean {
        if (rateUsPreferences.isNeverShownAgainClicked()) {
            // User doesn't want to see "rate us" again
            return false
        }

        if (rateUsPreferences.isRateNowClicked()) {
            // User already rated the app
            return false
        }

        if (buyPreferences.getBuyCount() < 2) {
            // "Rate us" should be shown after 2 "buy" clicked
            return false
        }

        val timeFromLastShown = time.getCurrentTimeMillis() - rateUsPreferences.getLastShownTimeMillis()
        if (timeFromLastShown < TWO_MONTHS_IN_MILLS) {
            // Show "rate us" only first time or if passed two months since last shown time
            return false
        }

        return true
    }

}
