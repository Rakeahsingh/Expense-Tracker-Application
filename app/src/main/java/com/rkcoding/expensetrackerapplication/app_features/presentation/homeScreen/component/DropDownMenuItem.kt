package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun DropDownMenuItem(
    isExpended: Boolean,
    onDismissRequest: () -> Unit,
    onTodayMenuClick: () -> Unit,
    onMonthlyMenuClick: () -> Unit,
    allTransactionClick:() -> Unit
) {
    
    DropdownMenu(
        expanded = isExpended,
        onDismissRequest = { onDismissRequest() },
        offset = DpOffset(
            x = 200.dp,
            y = 0.dp
        )
    ) {
        DropdownMenuItem(
            text = {
                   Text(text = "Today Transaction")
            },
            onClick = { onTodayMenuClick() }
        )

        DropdownMenuItem(
            text = {
                Text(text = "Monthly Transaction")
            },
            onClick = { onMonthlyMenuClick() }
        )

        DropdownMenuItem(
            text = {
                Text(text = "All Transaction")
            },
            onClick = { allTransactionClick() }
        )

    }
    
}