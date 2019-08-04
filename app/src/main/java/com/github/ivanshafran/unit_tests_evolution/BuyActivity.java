package com.github.ivanshafran.unit_tests_evolution;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BuyActivity extends AppCompatActivity implements RateUsDialog.Listener {

    private BuyPreferences buyPreferences;
    private ShowRateUsLogic showRateUsLogic;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        buyPreferences = new BuyPreferences(this);
        showRateUsLogic = new ShowRateUsLogic(
                new RateUsPreferences(this),
                buyPreferences,
                new Time()
        );

        findViewById(R.id.buyButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                buyPreferences.incrementBuyCount();
                if (showRateUsLogic.shouldShowRateUs()) {
                    showRateUs();
                }
            }
        });
    }

    private void showRateUs() {
        new RateUsDialog().show(getSupportFragmentManager(), null);
    }

    @Override
    public void onRateNowClick() {
        // Go to Google Play
    }
}
