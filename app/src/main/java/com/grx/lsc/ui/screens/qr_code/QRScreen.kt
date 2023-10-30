package com.grx.lsc.ui.screens.qr_code

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import com.google.zxing.integration.android.IntentIntegrator


@Composable
fun QRScannerScreen(){
    QRScannerWithPermission(){

    }
}
@Composable
fun QRScannerWithPermission(
    onQRScanned: (String) -> Unit
) {
    val context = LocalContext.current

    // Request camera permission
    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                startQRScanner(context, onQRScanned)
            }
        }

    // Check camera permission
    val hasCameraPermission = remember {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    if (hasCameraPermission) {
        startQRScanner(context, onQRScanned)
    } else {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }
}

private fun startQRScanner(context: Context, onQRScanned: (String) -> Unit) {
    val integrator = IntentIntegrator.forSupportFragment(null)
    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
    integrator.setPrompt("Scan a QR Code")
    integrator.setBeepEnabled(false)
    integrator.setOrientationLocked(false)
    integrator.initiateScan()
}
