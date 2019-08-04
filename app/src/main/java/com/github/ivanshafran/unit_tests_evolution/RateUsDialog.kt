package com.github.ivanshafran.unit_tests_evolution

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

class RateUsDialog : DialogFragment(), DialogInterface.OnClickListener {

    private lateinit var rateUsPreferences: RateUsPreferences

    private val listener: Listener
        get() = requireActivity() as Listener? ?: throw IllegalStateException("Activity should implement Listener")

    interface Listener {

        fun onRateNowClick()

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        rateUsPreferences = RateUsPreferences(requireContext())
        rateUsPreferences.setLastShownTimeMillis(Time().getCurrentTimeMillis())
        return AlertDialog.Builder(requireContext())
            .setMessage(R.string.rate_us_message)
            .setPositiveButton(R.string.rate_now, this)
            .setNeutralButton(R.string.remind_later, this)
            .setNegativeButton(R.string.never_show_again, this)
            .create()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                rateUsPreferences.setRateNowClickedClicked()
                listener.onRateNowClick()
            }
            DialogInterface.BUTTON_NEUTRAL -> {
                // Do nothing
            }
            DialogInterface.BUTTON_NEGATIVE -> rateUsPreferences.setNeverShownAgainClicked()
            else -> throw IllegalArgumentException("Unknown button click")
        }
    }
}
