package com.github.ivanshafran.unit_tests_evolution;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class RateUsDialog extends DialogFragment implements DialogInterface.OnClickListener {

    public interface Listener {

        void onRateNowClick();

    }

    private RateUsPreferences rateUsPreferences;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        rateUsPreferences = new RateUsPreferences(requireContext());
        rateUsPreferences.setLastShownTimeMillis(new Time().getCurrentTimeMillis());
        return new AlertDialog
                .Builder(requireContext())
                .setMessage(R.string.rate_us_message)
                .setPositiveButton(R.string.rate_now, this)
                .setNeutralButton(R.string.remind_later, this)
                .setNegativeButton(R.string.never_show_again, this)
                .create();
    }

    @Override
    public void onClick(final DialogInterface dialog, final int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                rateUsPreferences.setRateNowClickedClicked();
                getListener().onRateNowClick();
                break;
            case DialogInterface.BUTTON_NEUTRAL:
                // Do nothing
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                rateUsPreferences.setNeverShownAgainClicked();
                break;
            default:
                throw new IllegalArgumentException("Unknown button click");
        }
    }

    private Listener getListener() {
        return (Listener) getActivity();
    }
}
