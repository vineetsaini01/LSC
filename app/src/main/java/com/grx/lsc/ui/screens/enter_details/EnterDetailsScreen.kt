package com.grx.lsc.ui.screens.enter_details

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.os.BuildCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grx.lsc.R
import com.grx.lsc.ui.components.Loading
import java.util.Objects


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterDetailsScreen(
    state: EnterDetailsContract.State,
    event: (event: EnterDetailsContract.Event) -> Unit,
) {

    val context = LocalContext.current

    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

                    ||
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        })
    LaunchedEffect(true) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    if (hasCamPermission) {
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
                    title = "Upload Images",
                    onPressed = {
                        event(EnterDetailsContract.Event.OnPressedUploadImage)
                    }
                )

                UploadDocs(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    title = "Upload Document",
                    onPressed = {
                        event(EnterDetailsContract.Event.OnPressedUploadDoc)
                    }
                )
            }

            state.uris.forEachIndexed { index, uri ->
                ShowImageItem(title = "Docs ${index + 1}") {
                    event(EnterDetailsContract.Event.OnPressedRemoveBitmap(uri))
                }
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

    Loading(isLoading = state.isLoading)


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

@Composable
fun ShowImageItem(title: String, onPressedRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 8.dp))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(title)

            IconButton(onClick = { onPressedRemove() }) {
                Image(
                    painter = painterResource(id = R.drawable.do_not_disturb),
                    contentDescription = "do_not_disturb"
                )
            }


        }
    }
}



