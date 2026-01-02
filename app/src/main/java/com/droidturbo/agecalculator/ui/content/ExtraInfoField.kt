package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ExtraInfoField(
    title: String, details: Int
) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
    ) {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1 / 2f)
        )
        Text(
            text = "$details",
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1 / 2f)
        )
    }
}
