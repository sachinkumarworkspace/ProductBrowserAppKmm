package com.pba

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pba.navigation.NavigationApp
import com.pba.di.createKoinConfig
import org.koin.compose.KoinMultiplatformApplication
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    KoinMultiplatformApplication(
        config = createKoinConfig()
    ) {
        MaterialTheme {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    )
                    .fillMaxSize(),
            ) {
                NavigationApp()
            }
        }
    }
}