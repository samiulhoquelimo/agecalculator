package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ThreeColumnTitle(
    first: String, second: String, third: String
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = first,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1 / 3f)
            )
            Text(
                text = second,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1 / 3f)
            )
            Text(
                text = third,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1 / 3f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThreeColumnTitlePreview() {
    ThreeColumnTitle(
        first = "First",
        second = "Second",
        third = "Third"
    )
}