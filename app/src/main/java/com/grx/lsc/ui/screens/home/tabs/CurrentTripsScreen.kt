package com.grx.lsc.ui.screens.home.tabs


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grx.lsc.ui.components.CurrentTripsItem
import com.grx.lsc.ui.navigation.AppScreens
import com.grx.lsc.ui.screens.home.HomeViewModel


@Composable
fun CurrentTripsScreen(viewModel: HomeViewModel) {

    Column(modifier = Modifier.padding(top = 16.dp)) {
        CurrentTripsItem() {
            viewModel.navController.navigate(route = AppScreens.BoggieTrailer.route)
        }
    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurrentTripsScreenPreview() {
    CurrentTripsScreen(hiltViewModel())
}
