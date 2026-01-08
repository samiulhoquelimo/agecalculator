package com.droidturbo.agecalculator.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidturbo.agecalculator.ui.container.AgeContainer
import com.droidturbo.agecalculator.ui.container.BirthdayContainer
import com.droidturbo.agecalculator.ui.container.DateInputContainer
import com.droidturbo.agecalculator.ui.container.TotalInfoContainer
import com.droidturbo.agecalculator.ui.content.ContentSpacer
import com.droidturbo.agecalculator.ui.content.Divider
import com.droidturbo.agecalculator.ui.content.TopAppBar
import com.droidturbo.agecalculator.ui.theme.AppTypography
import com.droidturbo.agecalculator.ui.theme.lightScheme

@Composable
fun HomeScreen(
    modifier: Modifier, viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeScreenContent(
        modifier = modifier,
        state = state,
        onDayChange = viewModel::updateDay,
        onMonthChange = viewModel::updateMonth,
        onYearChange = viewModel::updateYear,
        onDateOfBirthChange = viewModel::onDateOfBirthChange,
        onSubmit = viewModel::calculate,
        onReset = viewModel::reset
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState(),
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    onDateOfBirthChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
    onReset: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Divider()
        ContentSpacer()
        DateInputContainer(
            state = state,
            onDayChange = onDayChange,
            onMonthChange = onMonthChange,
            onYearChange = onYearChange,
            onSubmit = onSubmit
        )
        ContentSpacer()
        state.result?.let {
            val (ageModel, nextBirthday, totalInfo) = state.result
            Divider()
            ContentSpacer()
            AgeContainer(ageModel = ageModel)
            ContentSpacer()
            BirthdayContainer(nextBirthday = nextBirthday)
            ContentSpacer()
            TotalInfoContainer(totalInfo = totalInfo)
            ContentSpacer()
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = AppTypography,
        content = {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding(),
                topBar = { TopAppBar() },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) { innerPadding ->
                HomeScreenContent(
                    modifier = Modifier.padding(paddingValues = innerPadding)
                )
            }
        }
    )
}