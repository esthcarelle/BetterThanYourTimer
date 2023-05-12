package com.mine.betterthanyourtimer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mine.betterthanyourtimer.components.TimerScreen
import com.mine.betterthanyourtimer.ui.theme.BetterThanYourTimerTheme
import com.mine.betterthanyourtimer.ui.theme.bgDarkBlue
import com.mine.betterthanyourtimer.ui.theme.purple
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
                    TimerScreen(modifier = Modifier, viewModel = TimerViewModel())
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    Scaffold(topBar = { MyTopAppBar(true, {}, {}) }) {
    }
}

@Composable
fun MyTopAppBar(isRunning: Boolean, onStartClicked: () -> Unit, onResetClicked: () -> Unit) {
    TopAppBar(elevation = 0.dp, modifier = Modifier.height(70.dp), backgroundColor = bgDarkBlue) {
        Box(modifier = Modifier.fillMaxSize()) {
            RotatingImage(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(top = 4.dp, start = 16.dp)
            )
            Text(
                text = "T I M E R",
                color = Color.White,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Center).padding(4.dp).wrapContentHeight()
            )
            IconButton(
                onClick = if (isRunning) onResetClicked else onStartClicked,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp)
                    .size(36.dp)
            ) {
                Icon(
                    imageVector = if (isRunning) Icons.Filled.Close else Icons.Default.PlayArrow,
                    contentDescription = "ToggleTimer",
                    tint = purple,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

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
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BetterThanYourTimerTheme {
        MyTopAppBar(false,{},{})
    }
}