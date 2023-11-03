package com.grx.lsc.ui.screens.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grx.lsc.R
import com.grx.lsc.ui.components.CustomTextField
import com.grx.lsc.ui.components.Loading

@Composable
fun LoginScreen(
    state: LoginContract.State,
    onEvent: (event: LoginContract.Event) -> Unit,
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

        Text(text = "Log in ")

        CustomTextField(
            value = state.mobileNo,
            leadingIcon = { Text("+91") },
            onValueChange = {
                onEvent(LoginContract.Event.OnChangedMobileNumber(it))
            },
            errorMessage = state.mobileError,
            label = "Mobile No"
        )

        Text(
            text = buildAnnotatedString {
                append("By continuing, You agree to our \n")
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("Terms of Service Privacy policy Content Policy")
                }
            },
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { onEvent(LoginContract.Event.SendOtp) },
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Send Otp")
        }
    }

    Loading(isLoading = state.isLoading)

}
