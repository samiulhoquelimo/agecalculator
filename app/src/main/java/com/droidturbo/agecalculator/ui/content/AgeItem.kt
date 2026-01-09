package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.utils.language

@Composable
fun AgeItem(
    label: String = "",
    value: Int = 0
) {
    Column(
        modifier = Modifier.padding(8.dp),
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
        Spacer(modifier = Modifier.height(4.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun AgeItemPreview() {
    AgeItem(
        label = "Age",
        value = 25
    )
}