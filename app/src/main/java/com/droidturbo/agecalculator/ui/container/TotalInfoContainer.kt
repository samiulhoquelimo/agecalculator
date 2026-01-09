package com.droidturbo.agecalculator.ui.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.data.HomeTotalModel
import com.droidturbo.agecalculator.data.toData
import com.droidturbo.agecalculator.ui.content.AppCard

@Composable
fun TotalInfoContainer(
    totalInfo: HomeTotalModel = HomeTotalModel()
) {
    AppCard(
        title = stringResource(R.string.some_extra_information)
    ) {
        totalInfo.toData().forEach { (label, value) ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = label.asString(),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TotalInfoContainerPreview() {
    TotalInfoContainer()
}