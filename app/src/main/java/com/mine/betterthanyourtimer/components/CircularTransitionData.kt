package com.mine.betterthanyourtimer.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.mine.betterthanyourtimer.ui.theme.green
import com.mine.betterthanyourtimer.ui.theme.red
import com.mine.betterthanyourtimer.ui.theme.yellow

class CircularTransitionData(
    progress: State<Float>,
    color: State<Color>
) {
    val progress by progress
    val color by color
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun updateCircularTransitionData(
    remainingTime: Long,
    totalTime: Long
): CircularTransitionData {
    val transition = updateTransition(targetState = remainingTime, label = "Circular transition")

    val progress = transition.animateFloat(
        transitionSpec = { tween(800, easing = LinearEasing) }, label = ""
    ) { remTime ->
        if (remTime < 0) {
            360f
        } else {
            360f - ((360f / totalTime) * (totalTime - remTime))
        }
    }

    val color = transition.animateColor(
        transitionSpec = {
            tween(800, easing = LinearEasing)
        }, label = "Color transition"
    ) {
        if (progress.value < 180f && progress.value > 90f) {
            yellow
        } else if (progress.value <= 90f) {
            red
        } else {
            green
        }
    }

    return remember(transition) { CircularTransitionData(progress = progress, color = color) }
}
