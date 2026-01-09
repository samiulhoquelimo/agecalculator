package com.droidturbo.agecalculator.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.droidturbo.agecalculator.data.AgeModel
import com.droidturbo.agecalculator.data.HomeDataModel
import com.droidturbo.agecalculator.data.BirthdayModel
import com.droidturbo.agecalculator.data.TotalInfoModel
import com.droidturbo.agecalculator.ui.container.AgeContainer
import com.droidturbo.agecalculator.ui.container.BirthdayContainer
import com.droidturbo.agecalculator.ui.container.DateInputContainer
import com.droidturbo.agecalculator.ui.container.TotalInfoContainer
import com.droidturbo.agecalculator.ui.content.TopAppBar
import com.droidturbo.agecalculator.ui.theme.AppTypography
import com.droidturbo.agecalculator.ui.theme.DividerColor
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
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(DividerColor)
        )
        Spacer(modifier = Modifier.height(16.dp))
        DateInputContainer(
            state = state,
            onDayChange = onDayChange,
            onMonthChange = onMonthChange,
            onYearChange = onYearChange,
            onSubmit = onSubmit
        )
        Spacer(modifier = Modifier.height(16.dp))

        val age = state.result?.age ?: AgeModel()
        val birthday = state.result?.nextBirthday ?: BirthdayModel()
        val total = state.result?.totalInfo ?: TotalInfoModel()

        AgeContainer(age)
        Spacer(modifier = Modifier.height(16.dp))
        BirthdayContainer(birthday)
        Spacer(modifier = Modifier.height(16.dp))
        TotalInfoContainer(total)
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
                topBar = { TopAppBar() },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) { innerPadding ->
                HomeScreenContent(
                    modifier = Modifier
                        .padding(paddingValues = innerPadding),
                    state = HomeState(
                        dayOfMonth = "01",
                        month = "01",
                        year = "2000",
                        result = HomeDataModel()
                    )
                )
            }
        }
    )
}