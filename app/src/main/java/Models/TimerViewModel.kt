package com.example.shopit

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {

    private var _timeLeft = MutableLiveData<Long>()
    val timeLeft: LiveData<Long> get() = _timeLeft

    private var timer: CountDownTimer? = null
    private var timeRemaining = 60000L // 1 minute in milliseconds

    init {
        startTimer()
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(timeRemaining, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                _timeLeft.value = millisUntilFinished
            }

            override fun onFinish() {
                timeRemaining = 0
                _timeLeft.value = 0
            }
        }.start()
    }

    fun restartTimer() {
        timeRemaining = 60000L
        startTimer()
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}
