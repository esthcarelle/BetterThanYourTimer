package com.mine.betterthanyourtimer.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mine.betterthanyourtimer.utils.getFormattedTime
import com.mine.betterthanyourtimer.ui.theme.*
import com.mine.betterthanyourtimer.viewModels.TimerViewModel

const val CIRCULAR_TIMER_RADIUS = 350f
const val TOTAL_TIME = TimerViewModel.totalTime

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TimerScreen(viewModel: TimerViewModel) {
    val remainingTime = viewModel.remainingTime.collectAsState().value
    val isRunning = viewModel.isRunning.collectAsState().value

    Scaffold(topBar = {
        MyTopAppBar(
            isRunning = isRunning,
            onStartClicked = { viewModel.startTimer() },
            onResetClicked = { viewModel.onResetTimer() })
    }, backgroundColor = bgDarkBlue) {
        Timer(remainingTime = remainingTime)
    }
}

@Composable
fun Timer(remainingTime: Long) {
    val transitionData = updateCircularTransitionData(
        remainingTime = remainingTime,
        totalTime = TOTAL_TIME
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Time(time = TOTAL_TIME)
            Spacer(modifier = Modifier.size(32.dp))

            CircularTimer(transitionData = transitionData)
            Spacer(modifier = Modifier.size(32.dp))

            CompletionBox(remainingTime = remainingTime, totalTime = TOTAL_TIME)

        }
        TimeRemaining(
            timeRemaining = remainingTime,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun TimeRemaining(modifier: Modifier, timeRemaining: Long) {
    val formattedTime = getFormattedTime(millis = timeRemaining)
    Text(
        text = formattedTime,
        color = red,
        style = MaterialTheme.typography.h4,
        modifier = modifier
    )
}

@Composable
fun RoundedBoxCorner(time: String, bg: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(bg)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {

        Text(
            text = time,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
        )
    }
}
