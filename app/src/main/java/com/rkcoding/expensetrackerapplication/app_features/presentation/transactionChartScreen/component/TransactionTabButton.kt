package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen.TransactionChartEvent
import com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen.TransactionChartViewModel
import com.rkcoding.expensetrackerapplication.ui.theme.GreenAlpha700
import com.rkcoding.expensetrackerapplication.ui.theme.Red500
import com.rkcoding.expensetrackerapplication.utils.TransactionType

@Composable
fun TransactionTabButton(
    tabs: List<TransactionType> = TransactionType.entries,
    cornerRadius: Dp = 24.dp,
    viewModel: TransactionChartViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

//    var selectedTabButton by remember { mutableStateOf(TransactionType.INCOME) }

    Surface(
        modifier = Modifier
            .padding(top = 4.dp, start = 16.dp, end = 16.dp),
        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f),
        shape = RoundedCornerShape(cornerRadius)
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            tabs.forEach { tab ->
                val backGroundColor by animateColorAsState(
                    if (state.transactionTab == tab && state.transactionTab.value == 0) GreenAlpha700
                    else if (state.transactionTab == tab && state.transactionTab.value == 1) Red500
                    else Color.Transparent,
                    animationSpec = tween(500, easing = LinearOutSlowInEasing),
                    label = "backGround color"
                )

                val textColor by animateColorAsState(
                    if (state.transactionTab == tab) Color.White
                    else Color.Black,
                    animationSpec = tween(500, easing = LinearOutSlowInEasing),
                    label = "backGround color"
                )

                TextButton(
                    onClick = {
                        viewModel.onEvent(TransactionChartEvent.OnTabButtonChange(tab))
//                          onTabButtonClick()
                    },
                    modifier = Modifier
                        .padding(
                            vertical = 4.dp
                        )
                        .weight(1f),
                    shape = RoundedCornerShape(cornerRadius),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backGroundColor,
                        contentColor = textColor
                    )
                ) {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp,
                                horizontal = 8.dp
                            )
                            .align(Alignment.CenterVertically)
                    )
                }

            }

        }

    }


}