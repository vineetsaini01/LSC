package com.grx.lsc.ui.screens.boggie_trailer

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BoggieTrailerViewModel @Inject constructor() : ViewModel() {

    lateinit var navController: NavHostController


}