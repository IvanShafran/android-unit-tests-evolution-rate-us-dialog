package com.github.ivanshafran.unit_tests_evolution.v1;

import com.github.ivanshafran.unit_tests_evolution.ShowRateUsLogic;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class ShowRateUsLogicTest {

    private RateUsPreferencesMock rateUsPreferences;
    private BuyPreferencesMock buyPreferences;
    private TimeMock time;

    private ShowRateUsLogic showRateUsLogic;

    @Test
    public void test1() {
        rateUsPreferences = new RateUsPreferencesMock();
        buyPreferences = new BuyPreferencesMock();
        time = new TimeMock();
        showRateUsLogic = new ShowRateUsLogic(
                rateUsPreferences,
                buyPreferences,
                time
        );

        buyPreferences.incrementBuyCount();
        time.setCurrentTimeMillis(new Date(2019, 6, 7).getTime());

        Assert.assertFalse(showRateUsLogic.shouldShowRateUs());
    }

}
