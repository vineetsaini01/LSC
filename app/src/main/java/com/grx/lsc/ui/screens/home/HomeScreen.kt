package com.grx.lsc.ui.screens.home

import android.annotation.SuppressLint
import android.content.res.Resources.Theme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grx.lsc.R
import com.grx.lsc.ui.screens.home.tabs.CurrentTripsScreen
import com.grx.lsc.ui.screens.home.tabs.HistoryScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Current Trips", "History")



    Scaffold(
        topBar = {
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
                        contentDescription ="home"
                    )
                    Text(text = "Home", color = Color.White)
                }

            }
        },

        ) {
        Box(modifier = Modifier.padding(it)) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {

                TabRow(selectedTabIndex = tabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> CurrentTripsScreen(viewModel)
                    1 -> HistoryScreen(viewModel)
                }

            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScreen(hiltViewModel())
}