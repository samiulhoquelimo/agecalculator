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
fun ThreeColumnField(
    year: Int, month: Int, day: Int
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = "$year",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1 / 3f)
        )
        Text(
            text = "$month",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1 / 3f)
        )
        Text(
            text = "$day",
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1 / 3f)
        )
    }
}
