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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.data.BirthdayModel
import com.droidturbo.agecalculator.ui.content.AgeItem
import com.droidturbo.agecalculator.ui.content.AppCard
import com.droidturbo.agecalculator.utils.language
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun BirthdayContainer(
    birthday: BirthdayModel = BirthdayModel()
) {
    val formattedDate = remember(birthday.dayOfMonth, birthday.month, birthday.year) {
        runCatching {
            val date = LocalDate.of(birthday.year, birthday.month, birthday.dayOfMonth)
            val formatter = DateTimeFormatter.ofPattern(
                "EEEE, dd MMMM yyyy",
                Locale.getDefault()
            )
            date.format(formatter)
        }.getOrNull()
    }

    AppCard(
        title = stringResource(R.string.next_birthday)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                AgeItem(
                    label = stringResource(id = R.string.months),
                    value = birthday.periodMonth
                )
                AgeItem(
                    label = stringResource(id = R.string.days),
                    value = birthday.periodDay
                )
            }
            AnimatedVisibility(
                visible = formattedDate != null,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = language(formattedDate),
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
        birthday = BirthdayModel(
            periodMonth = 5,
            periodDay = 4,
            dayOfMonth = 1,
            month = 3,
            year = 2026
        )
    )
}