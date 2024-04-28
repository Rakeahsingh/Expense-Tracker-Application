package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.HomeScreenEvent
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.HomeScreenViewModel
import com.rkcoding.expensetrackerapplication.utils.TabButton

@SuppressLint("SuspiciousIndentation")
@Composable
fun TabButton(
    tabs: List<TabButton> = TabButton.entries,
//    onTabButtonClick: () -> Unit = {},
    cornerRadius: Dp = 24.dp,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

//    var selectedTabButton by remember { mutableStateOf(TabButton.TODAY) }

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
                    if (state.tabButton == tab) MaterialTheme.colorScheme.primary
                    else Color.Transparent,
                    animationSpec = tween(500, easing = LinearOutSlowInEasing),
                    label = "backGround color"
                )

                val textColor by animateColorAsState(
                    if (state.tabButton == tab) Color.White
                    else Color.Black,
                    animationSpec = tween(500, easing = LinearOutSlowInEasing),
                    label = "backGround color"
                )

                TextButton(
                    onClick = {
                        viewModel.onEvent(HomeScreenEvent.OnTabValueChange(tab))

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