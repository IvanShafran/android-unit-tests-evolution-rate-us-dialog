package com.github.ivanshafran.unit_tests_evolution

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class BuyActivity : AppCompatActivity(), RateUsDialog.Listener {

    private lateinit var buyPreferences: BuyPreferences
    private lateinit var showRateUsLogic: ShowRateUsLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        buyPreferences = BuyPreferences(this)
        showRateUsLogic = ShowRateUsLogic(
            RateUsPreferences(this),
            buyPreferences,
            Time()
        )

        findViewById<View>(R.id.buyButton).setOnClickListener {
            buyPreferences.incrementBuyCount()
            if (showRateUsLogic.shouldShowRateUs()) {
                showRateUs()
            }
        }
    }

    private fun showRateUs() {
        RateUsDialog().show(supportFragmentManager, null)
    }

    override fun onRateNowClick() {
        // Go to Google Play
    }
}
