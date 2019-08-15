package com.github.ivanshafran.unit_tests_evolution.v1;

import com.github.ivanshafran.unit_tests_evolution.Time;

public class TimeMock implements Time {

    private long currentTimeMillis;

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }

    @Override
    public long getCurrentTimeMillis() {
        return 0;
    }
}
