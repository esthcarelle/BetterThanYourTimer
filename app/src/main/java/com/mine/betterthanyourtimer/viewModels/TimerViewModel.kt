package com.mine.betterthanyourtimer.viewModels

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerViewModel : ViewModel() {
    private var timer: CountDownTimer? = null

    private val _remainingTime = MutableStateFlow(totalTime)
    val remainingTime: StateFlow<Long> = _remainingTime

    private var _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning

    private fun createTimer() {
        timer = object : CountDownTimer(totalTime, interval) {
            override fun onTick(p0: Long) {
                _remainingTime.value = p0
            }

            override fun onFinish() {
                _remainingTime.value = totalTime
                _isRunning.value = false
            }

        }
    }

    fun startTimer() {
        createTimer()
        timer?.start()
        _isRunning.value = true
    }

    fun onResetTimer() {
        if (_isRunning.value) {
            timer?.cancel()
            _remainingTime.value = totalTime
            _isRunning.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
        _remainingTime.value = totalTime
    }

    companion object {
        const val totalTime = 60 * 1000L
        const val interval = 1 * 1000L
    }
}
