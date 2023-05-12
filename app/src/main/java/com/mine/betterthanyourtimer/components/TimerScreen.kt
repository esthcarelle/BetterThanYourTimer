package com.mine.betterthanyourtimer.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mine.betterthanyourtimer.getFormattedTime
import com.mine.betterthanyourtimer.ui.theme.*
import com.mine.betterthanyourtimer.viewModels.TimerViewModel
import com.mine.betterthanyourtimer.R

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
fun Time(time: Long) {
    val formattedTime = getFormattedTime(millis = time)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.title), color = green, style = MaterialTheme.typography.subtitle2)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = formattedTime,
            color = green,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
        )
    }

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

@Composable
fun CircularTimer(
    transitionData: CircularTransitionData,
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
            .requiredHeight(CIRCULAR_TIMER_RADIUS.dp)
    ) {
        inset(size.width / 2 - CIRCULAR_TIMER_RADIUS, size.height / 2 - CIRCULAR_TIMER_RADIUS) {
            drawCircle(
                color = gray,
                radius = CIRCULAR_TIMER_RADIUS,
                center = center,
                style = Stroke(width = 70f, cap = StrokeCap.Round)
            )

            drawArc(
                startAngle = 270f, // 270 is 0 degree
                sweepAngle = transitionData.progress,
                useCenter = false,
                color = transitionData.color,
                style = Stroke(width = 70f, cap = StrokeCap.Round)
            )
        }
    }
}