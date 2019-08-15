package com.github.ivanshafran.unit_tests_evolution.v1;

import com.github.ivanshafran.unit_tests_evolution.BuyPreferences;

public class BuyPreferencesMock implements BuyPreferences {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void incrementBuyCount() {
        ++count;
    }

    @Override
    public int getBuyCount() {
        return count;
    }
}
