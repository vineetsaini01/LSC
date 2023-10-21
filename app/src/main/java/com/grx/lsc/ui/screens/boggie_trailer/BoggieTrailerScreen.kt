package com.grx.lsc.ui.screens.boggie_trailer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.grx.lsc.R
import com.grx.lsc.ui.components.AlertDialogWrapper
import com.grx.lsc.ui.components.AlertDialogWrapperWithTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoggieTrailerScreen(
    navController: NavHostController,
    viewModel: BoggieTrailerViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit){
        viewModel.navController=navController
    }
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Boggie No", "Trailer NO")

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        bottomBar = {
            Column {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = viewModel::onClickConfirm,
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00920F),
                        contentColor = Color.White
                    )
                ) {
                    Text("Yes, Confirm")
                }
                Spacer(modifier = Modifier.height(10.dp))


                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5E5E),
                        contentColor = Color.White
                    )
                ) {
                    Text("Change Boggie/Trailer Number")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

            TabRow(selectedTabIndex = tabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            when (tabIndex) {
                0 -> BoggieTrailerInnerView("Boggie Number: #16565AB")
                1 -> BoggieTrailerInnerView("Trailer Number #1536232")
            }

            if (viewModel.openDialog) {

                AlertDialogWrapperWithTopBar(
                    title = "Confirmed",
                    onDismissRequest = {
                        viewModel.openDialog = false
                    }
                ) {
                    Column {
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
                            onClick = { viewModel.openDialog = false },
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

            if (viewModel.openDialog2) {
                AlertDialogWrapper(
                    onDismissRequest = {}
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.right_done),
                            contentDescription = ""
                        )

                        Text(
                            text = "Your Trip Has Been Started",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 19.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF282D40),
                                textAlign = TextAlign.Center,
                            )
                        )

                    }


                }
            }

        }
    }

}

@Composable
fun BoggieTrailerInnerView(text: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(0xFFF9F9F9), shape = RoundedCornerShape(size = 8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF282D40),
                    letterSpacing = 0.28.sp,
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Registration Documents",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF1E5FAA),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.24.sp,
                    textDecoration = TextDecoration.Underline,
                )
            )
        }

    }
}

