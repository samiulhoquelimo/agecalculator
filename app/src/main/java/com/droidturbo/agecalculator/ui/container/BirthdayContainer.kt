package com.droidturbo.agecalculator.ui.container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.data.HomeNextBirthdayModel
import com.droidturbo.agecalculator.ui.content.AgeItem
import com.droidturbo.agecalculator.ui.content.AppCard
import com.droidturbo.agecalculator.utils.language

@Composable
fun BirthdayContainer(
    nextBirthday: HomeNextBirthdayModel = HomeNextBirthdayModel()
) {
    AppCard(
        title = stringResource(R.string.next_birthday)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Month / Day row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AgeItem(
                    label = stringResource(id = R.string.months),
                    value = nextBirthday.bdMonth
                )
                AgeItem(
                    label = stringResource(id = R.string.days),
                    value = nextBirthday.bdDay
                )
            }
            AnimatedVisibility(
                visible = nextBirthday.bdDate.isNotBlank(),
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = language(nextBirthday.bdDate),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BirthdayContainerPreview() {
    BirthdayContainer(
        nextBirthday = HomeNextBirthdayModel(
            bdMonth = 1,
            bdDay = 1,
            bdDate = "EEEE, dd MMMM yyyy"
        )
    )
}