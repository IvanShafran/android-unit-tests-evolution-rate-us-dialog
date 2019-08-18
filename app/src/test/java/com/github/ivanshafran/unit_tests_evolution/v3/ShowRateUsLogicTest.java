package com.github.ivanshafran.unit_tests_evolution.v3;

import android.content.Context;
import com.github.ivanshafran.sharedpreferencesmock.SPMockBuilder;
import com.github.ivanshafran.unit_tests_evolution.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ShowRateUsLogicTest {

    private RateUsPreferences rateUsPreferences;
    private BuyPreferences buyPreferences;
    private Time time;

    private ShowRateUsLogic showRateUsLogic;

    @Before
    public void setUp() {
        final Context mockedContext = new SPMockBuilder().createContext();
        rateUsPreferences = new RateUsPreferencesImpl(mockedContext);
        buyPreferences = new BuyPreferencesImpl(mockedContext);
        time = Mockito.mock(Time.class);

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
        Mockito.when(time.getCurrentTimeMillis()).thenReturn(calendar.getTimeInMillis() + TimeUnit.DAYS.toMillis(90));

        Assert.assertTrue(showRateUsLogic.shouldShowRateUs());
    }
}
