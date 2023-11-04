package com.grx.lsc.ui.screens.reached

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grx.lsc.R
import com.grx.lsc.ui.components.AlertDialogWrapper
import com.grx.lsc.ui.components.AlertDialogWrapperWithTopBar
import com.grx.lsc.ui.components.BorderButton
import com.grx.lsc.ui.components.Loading
import com.grx.lsc.ui.components.RoundedButton
import com.grx.lsc.ui.components.SmallDetails

@Composable
fun ReachedScreen(
    state: ReachedContract.State,
    event: (event: ReachedContract.Event) -> Unit,
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .shadow(
                    elevation = 40.dp,
                    spotColor = Color(0x0D000000),
                    ambientColor = Color(0x0D000000)
                )
                .background(color = Color.White, shape = RoundedCornerShape(size = 12.dp))

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                if (state.isWaitingLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.time_oclock),
                            contentDescription = "image description",
                            contentScale = ContentScale.None
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "WAITING FOR LOADING",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF949494),
                            )
                        )
                    }
                }

                Text(
                    text = "Job Number #365656OK",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(800),
                        color = Color(0xFF343434),
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
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
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SmallDetails(R.drawable.calendar_date, "2020/03/27 - 09:00")
                    SmallDetails(R.drawable.weight_scale, "12 Tons")
                }

                SmallDetails(
                    drawable = R.drawable.delivery, text = "Trucks with awnings"
                )

                if (!state.isWaitingLoading) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        BorderButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            title = "Delay",
                            onClick = {
                                event(ReachedContract.Event.OnPressedDelayBtn)
                            },
                            borderColor = Color(0xFFF60000)
                        )

                        BorderButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            title = "Reached",
                            onClick = {
                                event(ReachedContract.Event.OnPressedReachedBtn)
                            },
                            borderColor = Color(0xFF00920F)
                        )
                    }

                } else {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        BorderButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            title = "End Trip",
                            onClick = { },
                            borderColor = Color(0xFFF60000)
                        )

                        BorderButton(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            title = "Resume Trip",
                            onClick = {
                            },
                            borderColor = Color(0xFF00920F)
                        )
                    }
                }

                ShowReachedAlertDialog(
                    showReachedAlert = state.showReachedAlert,
                    onDismissRequest = {
                        event(ReachedContract.Event.OnDismissReachedAlert)
                    },
                    onPressedContainerGrounded = {
                        event(
                            ReachedContract.Event.UpdateStatus(status = "Container Grounded")
                        )
                    },
                    onPressedTrailerCut = {
                        event(
                            ReachedContract.Event.UpdateStatus(status = "Trailer Cut")
                        )
                    },
                    onPressedSkip = {
                        event(ReachedContract.Event.OnPressedSkipBtn)
                    }
                )


                ShowDelayAlertDialog(
                    showDelayAlert = state.showDelayAlert,
                    onDismissRequest = {
                        event(ReachedContract.Event.OnDismissDelayAlert)
                    },
                    onPressedDoneBtn = {
                        event(
                            ReachedContract.Event.UpdateStatus(
                                reason = it,
                                status = "delay"
                            )
                        )
                    }
                )


                Spacer(modifier = Modifier.height(10.dp))
            }


        }

        Loading(isLoading = state.isLoading)
    }

}

@Composable
fun ShowReachedAlertDialog(
    showReachedAlert: Boolean,
    onDismissRequest: () -> Unit,
    onPressedContainerGrounded: () -> Unit,
    onPressedTrailerCut: () -> Unit,
    onPressedSkip: () -> Unit,
) {
    if (showReachedAlert) {
        AlertDialogWrapper(
            onDismissRequest = onDismissRequest
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                RoundedButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Container Grounded",
                    backgroundColor = Color(0xFF0C831F),
                    onClick = onPressedContainerGrounded
                )

                RoundedButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Trailer Cut",
                    backgroundColor = Color(0xFFFF5E5E),
                    onClick = onPressedTrailerCut
                )

                TextButton(
                    onClick = onPressedSkip,
                    content = {
                        Text(
                            text = "Skip",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 17.95.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF1E5FAA),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.32.sp,
                                textDecoration = TextDecoration.Underline,
                            )
                        )
                    }
                )

            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDelayAlertDialog(
    showDelayAlert: Boolean,
    onDismissRequest: () -> Unit,
    onPressedDoneBtn: (reason: String) -> Unit,
) {

    if (showDelayAlert) {
        var selectedOption by remember {
            mutableStateOf(ReachedContract.delayListItems.first())
        }
        var otherText by remember {
            mutableStateOf("")
        }
        AlertDialogWrapperWithTopBar(
            title = "Select Delay Reason",
            onDismissRequest = onDismissRequest,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                ReachedContract.delayListItems.forEach { text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selectedOption),
                                onClick = {
                                    selectedOption = text
                                }
                            ),
                        verticalAlignment = CenterVertically
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                selectedOption = text
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color(0xFF0C831F),
                                unselectedColor = Color.LightGray,
                            )
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = otherText,
                    enabled = selectedOption == "Other",
                    onValueChange = { otherText = it },
                    label = { Text("Enter Text here") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                BorderButton(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Done",
                    borderColor = Color(0xFF0C831F),
                    onClick = {
                        onPressedDoneBtn(
                            if (selectedOption == "Other") otherText else selectedOption
                        )
                    }
                )
            }
        }
    }

}


