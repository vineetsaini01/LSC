package com.grx.lsc.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grx.lsc.R
import com.grx.lsc.ui.navigation.AppScreens

@Composable
fun CurrentTripsItem(onPressedScanQRCode: () -> Unit) {
    var hasExpended by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(size = 8.dp)
            )

    ) {

        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(size = 8.dp),
                    ),
                contentAlignment = Alignment.CenterStart
            ) {

                Text(
                    text = "Job Number #365656OK",
                    modifier = Modifier
                        .padding(start = 16.dp),
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(800),
                        color = androidx.compose.ui.graphics.Color.White,
                    )
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(44.dp)
                    .background(
                        color = Color(0xFFF2F2F2),
                        shape = RoundedCornerShape(size = 10.dp),
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                Text(
                    text = "Port 1",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(800),
                        color = Color(0xFF343434),
                    )
                )

                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")

                Text(
                    text = "Port 12",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(800),
                        color = Color(0xFF343434),
                    )
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SmallDetails(R.drawable.calendar_date, "2020/03/27 - 09:00")
                SmallDetails(R.drawable.weight_scale, "12 Tons")
            }

            SmallDetails(
                modifier = Modifier.padding(start = 16.dp),
                drawable = R.drawable.delivery, text = "Trucks with awnings"
            )
            if (!hasExpended) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        onClick = { hasExpended = true },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00920F),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Accept")
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF5E5E),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Reject")
                    }
                }
            } else {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(30.dp)
                        .background(
                            color = Color(0xFFF2F2F2),
                            shape = RoundedCornerShape(size = 8.dp),
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),

                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        SmallDetails(
                            drawable = R.drawable.document_text,
                            text = "Registration"
                        )

                        Image(
                            painter = painterResource(id = R.drawable.download),
                            contentDescription = "calendar_date"
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = {
                        onPressedScanQRCode()
                    },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00920F),
                        contentColor = Color.White
                    )
                ) {
                    Text("Scan QR Code")
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
        }


    }
}




//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CurrentTripsItemPreview() {
//    CurrentTripsItem {
//        viewModel.navController.navigate(route = AppScreens.BoggieTrailer.route)
//    }
//}
