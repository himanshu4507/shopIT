package com.example.shopit

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class OTPSignUp : Fragment() {

    private lateinit var resendTextView: TextView
    private lateinit var viewModel: TimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_otp_sign_up, container, false)
        resendTextView = view.findViewById(R.id.resendotp)

        viewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        viewModel.timeLeft.observe(viewLifecycleOwner) { millisLeft ->
            if (millisLeft > 0) {
                updateTimerText((millisLeft / 1000).toInt())
            } else {
                showResendClickable()
            }
        }

        return view
    }

    private fun updateTimerText(secondsLeft: Int) {
        val timeStr = String.format("%02d:%02d", secondsLeft / 60, secondsLeft % 60)
        val fullText = "Resend OTP in $timeStr second(s)"
        val spannable = SpannableString(fullText)
        val start = fullText.indexOf(timeStr)
        val end = start + timeStr.length

        spannable.setSpan(StyleSpan(Typeface.BOLD), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(Color.BLACK), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        resendTextView.text = spannable
    }

    private fun showResendClickable() {
        val fullText = "Resend OTP"
        val spannable = SpannableString(fullText)

        spannable.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(requireContext(), "OTP resent", Toast.LENGTH_SHORT).show()
                viewModel.restartTimer()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = false
                ds.color = Color.BLACK
                ds.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            }
        }, 0, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        resendTextView.text = spannable
        resendTextView.movementMethod = LinkMovementMethod.getInstance()
        resendTextView.highlightColor = Color.TRANSPARENT
    }
}

