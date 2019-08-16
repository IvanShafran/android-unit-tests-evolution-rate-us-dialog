package com.github.ivanshafran.unit_tests_evolution.v2;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.github.ivanshafran.unit_tests_evolution.ShowRateUsLogic;
import com.github.ivanshafran.unit_tests_evolution.v1.BuyPreferencesMock;
import com.github.ivanshafran.unit_tests_evolution.v1.RateUsPreferencesMock;
import com.github.ivanshafran.unit_tests_evolution.v1.TimeMock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShowRateUsLogicTest {

    private RateUsPreferencesMock rateUsPreferences;
    private BuyPreferencesMock buyPreferences;
    private TimeMock time;

    private ShowRateUsLogic showRateUsLogic;

    @Before
    public void setUp() {
        rateUsPreferences = new RateUsPreferencesMock();
        buyPreferences = new BuyPreferencesMock();
        time = new TimeMock();

        showRateUsLogic = new ShowRateUsLogic(
                rateUsPreferences,
                buyPreferences,
                time
        );
    }

    @Test
    public void onFirstCheckAndOneClickItShouldNotShow() {
        buyPreferences.incrementBuyCount();

        Assert.assertFalse(showRateUsLogic.shouldShowRateUs());
    }

    @Test
    public void onThreeClicksAndItShouldShow() {
        // clicked three times
        buyPreferences.incrementBuyCount();
        buyPreferences.incrementBuyCount();
        buyPreferences.incrementBuyCount();

        // set first dialog show time
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.JULY, 7);
        rateUsPreferences.setLastShownTimeMillis(calendar.getTimeInMillis());

        // set current time to be 90 days after first show
        time.setCurrentTimeMillis(calendar.getTimeInMillis() + TimeUnit.DAYS.toMillis(90));

        Assert.assertTrue(showRateUsLogic.shouldShowRateUs());
    }
}
