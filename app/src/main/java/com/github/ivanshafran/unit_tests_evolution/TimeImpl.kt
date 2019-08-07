package com.github.ivanshafran.unit_tests_evolution

class TimeImpl : Time {

    override fun getCurrentTimeMillis(): Long {
        return System.currentTimeMillis()
    }

}
