package com.grx.lsc.ui.screens.home.tabs


import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.grx.lsc.ui.components.CurrentTripsItem
import com.grx.lsc.ui.screens.home.HomeViewModel


@Composable
fun CurrentTripsScreen(viewModel: HomeViewModel) {

    CurrentTripsItem()

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CurrentTripsScreenPreview() {
    CurrentTripsScreen(hiltViewModel())
}
