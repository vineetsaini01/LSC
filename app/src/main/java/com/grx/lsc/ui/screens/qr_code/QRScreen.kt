package com.grx.lsc.ui.screens.qr_code

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.grx.lsc.R
import com.grx.lsc.core.scanner.BarCodeAnalyser
import com.grx.lsc.ui.components.AlertDialogWrapperWithTopBar
import com.grx.lsc.ui.components.RoundedButton


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@kotlin.OptIn(ExperimentalMaterial3Api::class)
@OptIn(ExperimentalGetImage::class)
@Composable
fun QRScannerScreen(
    state: QRCodeContract.State,
    event: (QRCodeContract.Event) -> Unit,
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProvideFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }
    var hasCamPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            hasCamPermission = granted
        })
    LaunchedEffect(true) {
        launcher.launch(Manifest.permission.CAMERA)
    }
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { event(QRCodeContract.Event.OnBackPressed) }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = ""
                            )
                        }
                        Text(text = "Scan Vehicle QR", fontSize = 17.sp)
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
    ) {


        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (hasCamPermission) {
                AndroidView(
                    modifier = Modifier
                        .padding(26.dp)
                        .fillMaxWidth()
                        .aspectRatio(1.1f),
                    factory = { context ->
                        val previewView = PreviewView(context)
                        val preview = Preview.Builder().build()
                        val selector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()
                        preview.setSurfaceProvider(previewView.surfaceProvider)
                        val imageAnalysis = ImageAnalysis.Builder()
                            .setTargetResolution(
                                Size(
                                    previewView.width,
                                    previewView.height,
                                )
                            )
                            .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                            .build()
                        imageAnalysis.setAnalyzer(
                            ContextCompat.getMainExecutor(context),
                            BarCodeAnalyser {
                                event(QRCodeContract.Event.OnChangedQrCode(it))
                            }
                        )
                        try {
                            cameraProvideFuture.get().bindToLifecycle(
                                lifecycleOwner,
                                selector,
                                preview,
                                imageAnalysis
                            )
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                        previewView

                    },
                )
                RoundedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(26.dp),
                    title = "Scan QR Code",
                    onClick = {
                        event(QRCodeContract.Event.ScanQRCode)
                    },
                )
                if (state.code.isNotBlank()) {
                    AlertDialogWrapperWithTopBar(
                        title = "Confirmed",
                        onDismissRequest = {
                            event(QRCodeContract.Event.OnChangedQrCode(""))
                        }
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))

                            if (state.isLoading) {
                                CircularProgressIndicator(
                                    strokeWidth = 2.dp,
                                    modifier = Modifier.size(60.dp)
                                )
                            }
                            if (state.success) {
                                Image(
                                    painter = painterResource(id = R.drawable.right_done),
                                    contentDescription = ""
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Vehicle Number is MP 09 KC 0545",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    lineHeight = 29.67.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFF000000),
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 0.34.sp,
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                onClick = { event(QRCodeContract.Event.UploadVehicleNumber) },
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF00920F),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Yes, Confirm")
                            }
                        }
                    }
                }

            }
        }

    }
}

