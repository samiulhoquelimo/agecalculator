package com.droidturbo.agecalculator.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.droidturbo.agecalculator.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar() {
    val appName = LocalContext.current.resources.getText(R.string.app_name).toString()
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    appName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
    )
}


@Preview
@Composable
fun AddAppBarPreview() {
    AddAppBar()
}
