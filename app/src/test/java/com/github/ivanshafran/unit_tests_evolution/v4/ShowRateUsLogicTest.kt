package com.github.ivanshafran.unit_tests_evolution.v4

import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder
import com.github.ivanshafran.unit_tests_evolution.*
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit

class ShowRateUsLogicTest {

    companion object {
        private val SOME_DAY_IN_MILLIS: Long
        private val LESS_THAN_TWO_MONTHS = TimeUnit.DAYS.toMillis(59)
        private val MORE_THAN_TWO_MONTHS = TimeUnit.DAYS.toMillis(61)

        init {
            val calendar = Calendar.getInstance()
            calendar.set(2019, Calendar.JULY, 7)
            SOME_DAY_IN_MILLIS = calendar.timeInMillis
        }
    }

    private lateinit var rateUsPreferences: RateUsPreferences
    private lateinit var buyPreferences: BuyPreferences
    private lateinit var time: Time

    private lateinit var showRateUsLogic: ShowRateUsLogic

    @Before
    fun setUp() {
        val mockedContext = SPMockBuilder().createContext()
        rateUsPreferences = RateUsPreferencesImpl(mockedContext)
        buyPreferences = BuyPreferencesImpl(mockedContext)
        time = mock()

        showRateUsLogic = ShowRateUsLogic(
            rateUsPreferences,
            buyPreferences,
            time
        )
    }

    private fun prepareConditions(
        buyClickedTimes: Int = 0,
        isNeverShownAgainClicked: Boolean = false,
        isRateNowClicked: Boolean = false,
        lastShownTimeMillis: Long = 0,
        currentTimeMillis: Long = 0
    ) {
        repeat(buyClickedTimes) { buyPreferences.incrementBuyCount() }

        if (isNeverShownAgainClicked) rateUsPreferences.setNeverShownAgainClicked()
        if (isRateNowClicked) rateUsPreferences.setRateNowClickedClicked()
        rateUsPreferences.setLastShownTimeMillis(lastShownTimeMillis)

        whenever(time.getCurrentTimeMillis()).thenReturn(currentTimeMillis)
    }

    @Test
    fun onFirstCheckAndOneClickItShouldNotShow() {
        prepareConditions(buyClickedTimes = 1)

        Assert.assertFalse(showRateUsLogic.shouldShowRateUs())
    }

    @Test
    fun onThreeClicksAndItShouldShow() {
        prepareConditions(
            buyClickedTimes = 3,
            lastShownTimeMillis = SOME_DAY_IN_MILLIS,
            currentTimeMillis = SOME_DAY_IN_MILLIS + MORE_THAN_TWO_MONTHS
        )

        Assert.assertTrue(showRateUsLogic.shouldShowRateUs())
    }
}
