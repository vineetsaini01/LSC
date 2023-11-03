package com.grx.lsc.ui.screens.home.tabs


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grx.lsc.R
import com.grx.lsc.ui.components.SmallDetails
import com.grx.lsc.ui.screens.home.HomeContract


@Composable
fun CurrentTripsScreen(
    state: HomeContract.State,
    event: (event: HomeContract.Event) -> Unit,
) {


    state.driverJobDetailsRes?.data?.let { data ->
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(top = 16.dp)

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
                        text = "Job Number #${data.job_number}",
                        modifier = Modifier
                            .padding(start = 16.dp),
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight(800),
                            color = Color.White,
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
                        text = "Port 2",
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
                    SmallDetails(R.drawable.calendar_date, data.job_created_at!!)
                    SmallDetails(R.drawable.weight_scale, "${data.weight} Tons")
                }

                SmallDetails(
                    modifier = Modifier.padding(start = 16.dp),
                    drawable = R.drawable.delivery, text = "Trucks with awnings"
                )
                if (!state.hasExpended) {

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
                            onClick = {
                                event(HomeContract.Event.OnPressedAcceptOrReject("accept"))
                            },
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
                            onClick = {
                                event(HomeContract.Event.OnPressedAcceptOrReject("reject"))
                            },
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
                                text = "Registration",
                                modifier = Modifier.clickable {
                                    event(HomeContract.Event.OnPressedDocDownload)
                                }
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
                            event(HomeContract.Event.OnPressedQrCode)
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

}


