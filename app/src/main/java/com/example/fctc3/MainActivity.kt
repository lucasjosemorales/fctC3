@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.fctc3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.ui.ExperimentalComposeUiApi

import com.example.fctc3.navigation.AppNavigation
import com.example.fctc3.themes.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppTheme {
                AppNavigation()
                //PruebaScreen()
            }
        }
    }
}





