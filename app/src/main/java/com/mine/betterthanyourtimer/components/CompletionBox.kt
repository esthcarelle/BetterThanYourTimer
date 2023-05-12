package com.mine.betterthanyourtimer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mine.betterthanyourtimer.ui.theme.purple
import com.mine.betterthanyourtimer.ui.theme.red

@Composable
fun CompletionBox(remainingTime: Long, totalTime: Long) {
    val remaining = kotlin.math.ceil(((remainingTime * 100) / totalTime).toDouble()).toInt()
    val completed = 100 - remaining

    Row(
        modifier = Modifier
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundedBoxCorner(time = "COMPLETED $completed", bg = purple)
        RoundedBoxCorner(time = "REMAINING $remaining", bg = red)
    }
}
@Preview
@Composable
fun CompletionBoxPreview() {
    CompletionBox(remainingTime = 10, totalTime = 100)
}
