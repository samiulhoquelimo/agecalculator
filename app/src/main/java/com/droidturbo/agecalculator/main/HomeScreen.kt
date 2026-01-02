package com.droidturbo.agecalculator.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidturbo.agecalculator.ui.content.AddAppBar
import com.droidturbo.agecalculator.ui.content.CardBlock
import com.droidturbo.agecalculator.ui.content.ExtraInfoField
import com.droidturbo.agecalculator.ui.content.InputDateOfBirth
import com.droidturbo.agecalculator.ui.content.ThreeColumnField
import com.droidturbo.agecalculator.ui.content.ThreeColumnTitle
import com.droidturbo.agecalculator.ui.content.TitleBlock
import com.droidturbo.agecalculator.ui.content.TwoColumnField
import com.droidturbo.agecalculator.ui.content.TwoColumnTitle
import com.droidturbo.agecalculator.ui.theme.AppTheme

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    HomeScreenContent(
        modifier = modifier,
        state = state,
        reset = viewModel::reset,
        calculation = viewModel::calculate
    )
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState = HomeState(),
    reset: () -> Unit = {},
    calculation: (Int, Int, Int) -> Unit = { _, _, _ -> },
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        CardBlock {
            Column {
                TitleBlock(text = "Enter your Date of Birth")
                InputDateOfBirth(reset, calculation)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardBlock {
            Column(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TitleBlock(text = "Your age is")
                ThreeColumnTitle("Year", "Month", "Days")
                ThreeColumnField(state.ageYear, state.ageMonth, state.ageDay)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardBlock {
            Column(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TitleBlock(text = "Next birthday")
                TwoColumnTitle("Month", "Day")
                TwoColumnField(state.bdMonth, state.bdDay)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        CardBlock {
            Column(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                TitleBlock(text = "Some extra information")
                ExtraInfoField("Total Year: ", state.tYear)
                ExtraInfoField("Total Month: ", state.tMonth)
                ExtraInfoField("Total Weeks: ", state.tWeek)
                ExtraInfoField("Total Days: ", state.tDay)
                ExtraInfoField("Total Hours: ", state.tHour)
                ExtraInfoField("Total Minutes: ", state.tMin)
                ExtraInfoField("Total Seconds: ", state.tSec)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomeScreenPreview() {
    AppTheme {
        Scaffold(
            topBar = { AddAppBar() },
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) { innerPadding ->
            HomeScreenContent(Modifier.padding(innerPadding))
        }
    }
}