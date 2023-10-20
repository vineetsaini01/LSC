package com.grx.lsc.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grx.lsc.ui.screens.home.tabs.CurrentTripsScreen
import com.grx.lsc.ui.screens.home.tabs.HistoryScreen

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Current Trips", "History")

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePreview() {
    HomeScreen(hiltViewModel())
}