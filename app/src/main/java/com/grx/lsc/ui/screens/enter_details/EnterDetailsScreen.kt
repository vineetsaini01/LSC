package com.grx.lsc.ui.screens.enter_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.grx.lsc.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterDetailsScreen(
    navController: NavHostController,
    viewModel: EnterDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit){
        viewModel.navController=navController
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.containerNo,
            onValueChange = { viewModel.containerNo = it },
            label = { Text("Container No") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = viewModel.sealNo,
            onValueChange = { viewModel.sealNo = it },
            label = { Text("Seal No") }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            UploadDocs(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                title = "Upload Image"
            )

            UploadDocs(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                title = "Upload Image"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // You can adjust the padding as needed
                onClick = { viewModel.onPressedDone()},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF00920F),
                    contentColor = Color.White
                )
            ) {
                Text("Done")
            }
        }


    }


}

@Composable
fun UploadDocs(title: String, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .height(90.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(size = 12.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(painter = painterResource(id = R.drawable.upload), contentDescription = "")
        Text(
            text = title,
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
                letterSpacing = 0.24.sp,
            )
        )


    }

}



