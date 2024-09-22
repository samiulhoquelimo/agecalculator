package com.droidturbo.agecalculator.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidturbo.agecalculator.ui.components.AddAppBar
import com.droidturbo.agecalculator.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    topBar = { AddAppBar() },
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) { innerPadding ->
                    val viewModel = hiltViewModel<HomeViewModel>()
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

