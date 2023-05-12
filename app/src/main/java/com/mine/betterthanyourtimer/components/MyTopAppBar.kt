package com.mine.betterthanyourtimer.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mine.betterthanyourtimer.ui.theme.BetterThanYourTimerTheme
import com.mine.betterthanyourtimer.ui.theme.bgDarkBlue
import com.mine.betterthanyourtimer.ui.theme.purple

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
                text = stringResource(id = com.mine.betterthanyourtimer.R.string.title),
                color = Color.White,
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(4.dp)
                    .wrapContentHeight()
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
                    contentDescription = stringResource(id = com.mine.betterthanyourtimer.R.string.toggle_timer),
                    tint = purple,
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BetterThanYourTimerTheme {
        MyTopAppBar(false,{},{})
    }
}

