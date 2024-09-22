package com.droidturbo.agecalculator.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardBlock(
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary,
        ),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp,
            focusedElevation = 6.dp,
            hoveredElevation = 10.dp
        ),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun CardBlockPreview() {
    CardBlock {
        TitleBlock("Inside a card block")
    }
}