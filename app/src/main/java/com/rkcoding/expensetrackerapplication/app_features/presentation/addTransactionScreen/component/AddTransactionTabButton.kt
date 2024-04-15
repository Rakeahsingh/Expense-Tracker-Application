package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component

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
import androidx.compose.material.TextButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rkcoding.expensetrackerapplication.utils.TransactionType

@Composable
fun AddTransactionTabButton(
    tabs: Array<TransactionType> = TransactionType.entries.toTypedArray(),
    onTabClick: () -> Unit = {},
    cornerRadius: Dp = 24.dp
) {

    var selectedTab by remember {
        mutableStateOf(TransactionType.INCOME)
    }

    Surface(
        modifier = Modifier
            .padding(top = 4.dp, start = 8.dp, end = 8.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            tabs.forEach { tab ->
                val backgroundColor by animateColorAsState(
                    targetValue = if (selectedTab == tab) MaterialTheme.colorScheme.primary else Color.Transparent,
                    label = "background color",
                    animationSpec = tween(500, easing = LinearOutSlowInEasing)
                )

                val textColor by animateColorAsState(
                    targetValue = if (selectedTab == tab) Color.White else Color.Black,
                    label = "text color",
                    animationSpec = tween(500, easing = LinearOutSlowInEasing)
                )

                TextButton(
                    onClick = {
                        selectedTab = tab
                        onTabClick()
                    },
                    modifier = Modifier
                        .padding(
                            vertical = 4.dp
                        )
                        .weight(1f),
                    shape = RoundedCornerShape(cornerRadius),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = backgroundColor,
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