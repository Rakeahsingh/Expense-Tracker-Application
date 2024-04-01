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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rkcoding.expensetrackerapplication.ui.theme.LightBlack
import com.rkcoding.expensetrackerapplication.utils.TabButton

@SuppressLint("SuspiciousIndentation")
@Composable
fun TabButton(
    tabs: Array<TabButton> = TabButton.entries.toTypedArray(),
    onTabButtonClick: () -> Unit = {},
    cornerRadius: Dp = 24.dp
) {

    var selectedTabButton by remember { mutableStateOf(TabButton.TODAY) }

    Surface(
        modifier = Modifier
            .padding(top = 4.dp, start = 16.dp, end = 16.dp),
        color = Color.Gray.copy(alpha = 0.5f),
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
                    if (selectedTabButton == tab) LightBlack
                    else Color.Transparent,
                    animationSpec = tween(500, easing = LinearOutSlowInEasing),
                    label = "backGround color"
                )

                val textColor by animateColorAsState(
                    if (selectedTabButton == tab) MaterialTheme.colors.surface
                    else MaterialTheme.colors.onSurface,
                    animationSpec = tween(500, easing = LinearOutSlowInEasing),
                    label = "backGround color"
                )

                TextButton(
                    onClick = {
                        selectedTabButton = tab
                          onTabButtonClick()
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
                        style = MaterialTheme.typography.subtitle2,
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