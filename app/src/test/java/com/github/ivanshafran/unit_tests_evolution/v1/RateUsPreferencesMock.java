package com.github.ivanshafran.unit_tests_evolution.v1;

import com.github.ivanshafran.unit_tests_evolution.RateUsPreferences;

public class RateUsPreferencesMock implements RateUsPreferences {

    private long lastShownTimeMillis;
    private boolean isNeverShownAgainClicked;
    private boolean isRateNowClicked;

    @Override
    public long getLastShownTimeMillis() {
        return lastShownTimeMillis;
    }

    @Override
    public void setLastShownTimeMillis(long timeMillis) {
        lastShownTimeMillis = timeMillis;
    }

    @Override
    public boolean isNeverShownAgainClicked() {
        return isNeverShownAgainClicked;
    }

    @Override
    public void setNeverShownAgainClicked() {
        isNeverShownAgainClicked = true;
    }

    @Override
    public boolean isRateNowClicked() {
        return isRateNowClicked;
    }

    @Override
    public void setRateNowClickedClicked() {
        isRateNowClicked = true;
    }
}
