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
        val timeFromLastShown = time.getCurrentTimeMillis() - rateUsPreferences.getLastShownTimeMillis()
        return when {
            // User doesn't want to see "rate us" again
            rateUsPreferences.isNeverShownAgainClicked() -> false
            // User already rated the app
            rateUsPreferences.isRateNowClicked() -> false
            // "Rate us" should be shown after 2 "buy" clicked
            buyPreferences.getBuyCount() < 2 -> false
            // Show "rate us" only first time or if passed two months since last shown time
            timeFromLastShown < TWO_MONTHS_IN_MILLS -> false
            else -> true
        }
    }

}
