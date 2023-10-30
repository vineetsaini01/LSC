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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grx.lsc.R
import com.grx.lsc.ui.components.CustomTextField
import com.grx.lsc.ui.components.Loading

@Composable
fun LoginScreen(viewModel: LoginViewModel) {

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

//        OutlinedTextField(
//            modifier = Modifier.fillMaxWidth(),
//            value = viewModel.mobileN0,
//            leadingIcon = {Text("+91")},
//            onValueChange = { viewModel.mobileN0=it },
//            label = { Text("Mobile No") }
//        )

        CustomTextField(
            value = viewModel.mobileN0,
            leadingIcon = { Text("+91") },
            onValueChange = {
                viewModel.onEvent(LoginEvent.OnChangedMobileNumber(it))
            },
            errorMessage = viewModel.mobileError,
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
            onClick = { viewModel.onEvent(LoginEvent.SendOtp) },
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("Send Otp")
        }
    }

    Loading(isLoading = viewModel.isLoading)

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(hiltViewModel())
}