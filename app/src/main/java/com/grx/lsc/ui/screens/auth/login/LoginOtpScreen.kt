package com.grx.lsc.ui.screens.auth.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grx.lsc.R
import com.grx.lsc.ui.components.OtpView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginOtpScreen(
    state: LoginContract.State,
    event: (event: LoginContract.Event) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { event(LoginContract.Event.OnBackPress) }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = ""
                            )
                        }
                        Text(text = "OTP Verification", fontSize = 17.sp)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(
                top = 143.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo"
            )

            Text(
                text = "We have sent a verification code to +91-${state.mobileNo}",
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )

            OtpView(
                onValueChange = {
                    event(LoginContract.Event.OnChangedMobileOTP(it))
                },
                otpValue = state.mobileOtp,
                errorMessage = state.mobileOTPError
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Watch Icon"
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "00:60",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "Resend SMS in 01:00",
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    event(LoginContract.Event.VerifyCode)
                },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text("Login")
            }
        }
    }

}