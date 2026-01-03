package com.droidturbo.agecalculator.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidturbo.agecalculator.ui.container.AgeContainer
import com.droidturbo.agecalculator.ui.container.BirthdayContainer
import com.droidturbo.agecalculator.ui.container.DateInputContainer
import com.droidturbo.agecalculator.ui.container.TotalInfoContainer
import com.droidturbo.agecalculator.ui.content.AddAppBar
import com.droidturbo.agecalculator.ui.content.Divider
import com.droidturbo.agecalculator.ui.theme.AppTypography
import com.droidturbo.agecalculator.ui.theme.lightScheme

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeScreenContent(
        modifier = modifier,
        state = state,
        onDayChange = viewModel::updateDay,
        onMonthChange = viewModel::updateMonth,
        onYearChange = viewModel::updateYear,
        onSubmit = viewModel::calculate
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState(),
    onDayChange: (String) -> Unit = {},
    onMonthChange: (String) -> Unit = {},
    onYearChange: (String) -> Unit = {},
    onSubmit: () -> Unit = {},
    onReset: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        DateInputContainer(
            state = state,
            onDayChange = onDayChange,
            onMonthChange = onMonthChange,
            onYearChange = onYearChange,
            onSubmit = onSubmit,
            onReset = onReset
        )
        Spacer(modifier = Modifier.height(16.dp))
        AgeContainer(state = state)
        Spacer(modifier = Modifier.height(16.dp))
        BirthdayContainer(state = state)
        Spacer(modifier = Modifier.height(16.dp))
        TotalInfoContainer(state = state)
        Spacer(modifier = Modifier.height(16.dp))
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
                topBar = { AddAppBar() },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) { innerPadding ->
                HomeScreenContent(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    )
}