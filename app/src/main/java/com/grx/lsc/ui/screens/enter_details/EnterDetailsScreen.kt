package com.grx.lsc.ui.screens.enter_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.core.os.BuildCompat
import com.grx.lsc.R
import java.util.Objects


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterDetailsScreen(
    state: EnterDetailsContract.State,
    event: (event: EnterDetailsContract.Event) -> Unit,
) {


    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.containerNo,
            onValueChange = { event(EnterDetailsContract.Event.OnChangedContainerNo(it)) },
            label = { Text("Container No") }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.sealNo,
            onValueChange = { event(EnterDetailsContract.Event.OnChangedSealNo(it)) },
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
                title = "Upload Image",
                onPressed = {

                }
            )

            UploadDocs(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                title = "Upload Image",
                onPressed = {

                }
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
                onClick = { event(EnterDetailsContract.Event.OnPressedDoneBtn) },
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
fun UploadDocs(title: String, modifier: Modifier = Modifier, onPressed: () -> Unit) {


    Column(
        modifier = modifier
            .height(90.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .background(color = Color.White, shape = RoundedCornerShape(size = 12.dp))
            .clickable {
                onPressed()
            },
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



