package com.mine.betterthanyourtimer.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.dp
import com.mine.betterthanyourtimer.ui.theme.gray

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