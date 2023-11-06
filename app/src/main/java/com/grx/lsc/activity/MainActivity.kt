package com.grx.lsc.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.grx.lsc.ui.navigation.AppNavHost
import com.grx.lsc.ui.navigation.AppNavigator
import com.grx.lsc.ui.theme.LscTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LscTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    appNavigator.setNavController(
                        navController = rememberNavController(),
                        owner = this
                    )
                    AppNavHost(
                        appNavigator = appNavigator
                    )
                }
            }
        }
    }

}

