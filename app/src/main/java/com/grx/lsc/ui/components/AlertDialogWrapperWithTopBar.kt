package com.grx.lsc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AlertDialogWrapperWithTopBar(
    title:String,
    onDismissRequest: () -> Unit,
    body: @Composable () -> Unit,
) {

    Dialog(onDismissRequest =  onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column {
                Box (
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .background(
                            color = Color(0xFFF1F5FF),
                            shape = RoundedCornerShape(size = 12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ){

                    Text(
                        text = title,
                        style = TextStyle(
                            fontSize = 17.sp,
                            lineHeight = 19.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF1A2E35),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.34.sp,
                        )
                    )

                    IconButton(
                        onClick = onDismissRequest,
                        modifier = Modifier.align(Alignment.CenterEnd) // Align to the right
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }


                }
                Box(modifier = Modifier.padding(20.dp)) {
                    body()
                }


            }

        }
    }

}


@Composable
fun AlertDialogWrapper(
    modifier: Modifier=Modifier,
    onDismissRequest: () -> Unit,
    body: @Composable () -> Unit,
) {

    Dialog(onDismissRequest =  onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(modifier = modifier.padding(20.dp)) {
                body()
            }

        }
    }

}