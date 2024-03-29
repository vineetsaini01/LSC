package com.grx.lsc.ui.screens.bottom_nav

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.grx.lsc.R
import com.grx.lsc.ui.navigation.BottomNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavScreen(viewModel: BottomViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val owner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.apply {
            bottomNavigator.setNavController(
                navController = navController,
                owner = owner
            )
            setDestinationChangedListener()
        }
    }

    Scaffold(
        topBar = {
            if (!viewModel.hasShowTopBottomNav)
                return@Scaffold

            TopAppBar(
                title = {},

                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            if (!viewModel.hasShowTopBottomNav)
                return@Scaffold
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(id = R.drawable.home),
                        contentDescription = "home"
                    )
                    Text(text = "Home", color = Color.White)
                }

            }
        },

        ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {

            if (viewModel.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                viewModel.driverJobDetailsRes?.let {
                    BottomNavHost(
                        bottomNavigator = viewModel.bottomNavigator,
                        driverJobDetailsRes = it
                    )
                }
            }
        }

    }

}