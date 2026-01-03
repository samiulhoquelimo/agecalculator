package com.droidturbo.agecalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.droidturbo.agecalculator.home.HomeScreen
import com.droidturbo.agecalculator.ui.content.TopAppBar
import com.droidturbo.agecalculator.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(
                    modifier = Modifier.Companion
                        .fillMaxSize()
                        .systemBarsPadding(),
                    topBar = { TopAppBar() },
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.Companion.padding(innerPadding)
                    )
                }
            }
        }
    }
}