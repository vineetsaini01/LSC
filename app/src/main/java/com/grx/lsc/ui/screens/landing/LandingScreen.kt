package com.grx.lsc.ui.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.grx.lsc.R
import com.grx.lsc.ui.components.AlertDialogWrapperWithTopBar
import com.grx.lsc.ui.components.RoundedButton
import com.grx.lsc.ui.navigation.AppRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(appNavController: NavHostController) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    var trackingID by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(
            top = 143.dp,
            start = 16.dp,
            end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (openDialog) {
            AlertDialogWrapperWithTopBar(
                title = "Enter Your Tracking ID",
                onDismissRequest = { openDialog = false },
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = trackingID,
                        onValueChange = { trackingID = it },
                        label = { Text("Tracking ID") }
                    )

                    RoundedButton(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Track Shipment",
                        onClick = { openDialog = false }
                    )
                }
            }
        }

        Image(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo"
        )


        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            RoundedButton(
                modifier = Modifier.fillMaxWidth(),
                title = "Track Your Shipment",
                onClick = { openDialog = true }
            )

            Spacer(modifier = Modifier.heightIn(10.dp))

            RoundedButton(
                modifier = Modifier.fillMaxWidth(),
                title = "Log In",
                onClick = {
                    appNavController.navigate(AppRoute.Login.route)
                }
            )

        }

    }
}