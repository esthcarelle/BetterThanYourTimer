package com.mine.betterthanyourtimer.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mine.betterthanyourtimer.R
import com.mine.betterthanyourtimer.utils.getFormattedTime
import com.mine.betterthanyourtimer.ui.theme.green

@Composable
fun Time(time: Long) {
    val formattedTime = getFormattedTime(millis = time)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.title),
            color = green,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = formattedTime,
            color = green,
            style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
        )
    }
}
