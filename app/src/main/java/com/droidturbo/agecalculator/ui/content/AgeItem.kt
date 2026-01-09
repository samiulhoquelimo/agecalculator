package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.droidturbo.agecalculator.utils.language

@Composable
fun AgeItem(
    label: String,
    value: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = language(value.toString()),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}