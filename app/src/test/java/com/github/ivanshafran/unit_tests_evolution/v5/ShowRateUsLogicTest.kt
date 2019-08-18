package com.github.ivanshafran.unit_tests_evolution.v5

import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder
import com.github.ivanshafran.unit_tests_evolution.BuyPreferencesImpl
import com.github.ivanshafran.unit_tests_evolution.RateUsPreferencesImpl
import com.github.ivanshafran.unit_tests_evolution.ShowRateUsLogic
import com.github.ivanshafran.unit_tests_evolution.Time
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.*
import java.util.concurrent.TimeUnit

class ShowRateUsLogicTest : Spek({

    val calendar = Calendar.getInstance()
    calendar.set(2019, Calendar.JULY, 7)

    val SOME_DAY_IN_MILLIS = calendar.timeInMillis
    val LESS_THAN_TWO_MONTHS = TimeUnit.DAYS.toMillis(59)
    val MORE_THAN_TWO_MONTHS = TimeUnit.DAYS.toMillis(61)

    val mockedContext = SPMockBuilder().createContext()

    val rateUsPreferences by memoized { RateUsPreferencesImpl(mockedContext) }
    val buyPreferences by memoized { BuyPreferencesImpl(mockedContext) }
    val time by memoized { mock<Time>() }
    val showRateUsLogic by memoized {
        ShowRateUsLogic(
            rateUsPreferences,
            buyPreferences,
            time
        )
    }

    fun prepareConditions(
        buyClickedTimes: Int = 0,
        isNeverShownAgainClicked: Boolean = false,
        isRateNowClicked: Boolean = false,
        lastShownTimeMillis: Long = 0,
        currentTimeMillis: Long = System.currentTimeMillis()
    ) {
        repeat(buyClickedTimes) { buyPreferences.incrementBuyCount() }

        if (isNeverShownAgainClicked) rateUsPreferences.setNeverShownAgainClicked()
        if (isRateNowClicked) rateUsPreferences.setRateNowClickedClicked()
        rateUsPreferences.setLastShownTimeMillis(lastShownTimeMillis)

        whenever(time.getCurrentTimeMillis()).thenReturn(currentTimeMillis)
    }

    describe("show rate us logic") {

        context("first conditions checks") {
            context("buy clicked once") {
                beforeEachTest {
                    prepareConditions(buyClickedTimes = 1)
                }

                it("should not show 'rate us'") {
                    Assert.assertFalse(showRateUsLogic.shouldShowRateUs())
                }
            }

            context("buy clicked two times") {
                beforeEachTest {
                    prepareConditions(buyClickedTimes = 2)
                }

                it("should show 'rate us'") {
                    Assert.assertTrue(showRateUsLogic.shouldShowRateUs())
                }
            }
        }

        context("'rate us' was shown already") {
            context("user clicked 'show me later' on the dialog") {
                context("less than two months passed and user clicks buy") {
                    beforeEachTest {
                        prepareConditions(
                            buyClickedTimes = 3,
                            lastShownTimeMillis = SOME_DAY_IN_MILLIS,
                            currentTimeMillis = SOME_DAY_IN_MILLIS + MORE_THAN_TWO_MONTHS
                        )
                    }

                    it("should show 'rate us' again") {
                        Assert.assertTrue(showRateUsLogic.shouldShowRateUs())
                    }
                }

                context("more than two months passed and user clicks buy") {
                    beforeEachTest {
                        prepareConditions(
                            buyClickedTimes = 3,
                            lastShownTimeMillis = SOME_DAY_IN_MILLIS,
                            currentTimeMillis = SOME_DAY_IN_MILLIS + MORE_THAN_TWO_MONTHS
                        )
                    }

                    it("should show 'rate us' again") {
                        Assert.assertTrue(showRateUsLogic.shouldShowRateUs())
                    }
                }
            }
        }
    }
})
