package com.mine.betterthanyourtimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.mine.betterthanyourtimer.components.TimerScreen
import com.mine.betterthanyourtimer.ui.theme.BetterThanYourTimerTheme
import com.mine.betterthanyourtimer.viewModels.TimerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BetterThanYourTimerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TimerScreen( viewModel = TimerViewModel())
                }
            }
        }
    }
}





