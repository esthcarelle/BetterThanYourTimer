package com.mine.betterthanyourtimer.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.imageResource
import com.mine.betterthanyourtimer.R

@Composable
fun RotatingImage(modifier: Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val angleState by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing)
        )
    )

    // you can add different px density image for different devices
    val image = ImageBitmap.imageResource(id = R.drawable.glassclock)
    val imageWidth = image.width
    val imageHeight = image.height

    Canvas(modifier = modifier.fillMaxSize()) {
        rotate(
            degrees = 360 * angleState,
            pivot = Offset(x = imageWidth.toFloat() / 2, y = imageHeight.toFloat() / 2)
        ) {
            drawImage(image = image)
        }
    }
}