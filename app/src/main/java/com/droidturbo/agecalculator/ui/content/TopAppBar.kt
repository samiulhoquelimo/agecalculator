package com.droidturbo.agecalculator.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.droidturbo.agecalculator.R
import com.droidturbo.agecalculator.utils.AppLanguage
import com.droidturbo.agecalculator.utils.language
import com.droidturbo.agecalculator.utils.switchLanguage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var currentLanguage by rememberSaveable { mutableStateOf(language()) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        actions = {
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = stringResource(R.string.change_language)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    AppLanguage.entries.forEach { language ->
                        DropdownMenuItem(
                            text = { Text(text = "${language.flag}  ${language.label}") },
                            onClick = {
                                expanded = false
                                currentLanguage = language.code
                                context.switchLanguage(language.code)
                            },
                            trailingIcon = {
                                if (language.code == currentLanguage) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Check Icon"
                                    )
                                }
                            }
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}


@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar()
}
