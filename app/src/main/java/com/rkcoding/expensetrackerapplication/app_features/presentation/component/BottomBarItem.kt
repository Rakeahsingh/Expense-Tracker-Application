package com.rkcoding.expensetrackerapplication.app_features.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBarItem(
    item: BottomBarItemHolder,
    onItemClick: () -> Unit,
    isSelected: Boolean
) {

    val backGroundColor = if (isSelected) MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f) else Color.Transparent
    val contentColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Gray

//    Box(
//        modifier = Modifier
//            .clip(RoundedCornerShape(12.dp))
//            .background(backGroundColor)
//            .padding(horizontal = 8.dp, vertical = 4.dp)
//            .clickable { onItemClick() }
//    ){

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { onItemClick() }
        ) {

            Icon(
                imageVector = if (isSelected) item.selectedIcon else item.unSelectedIcon,
                contentDescription = "icon images",
                tint = contentColor
            )

            Text(
                text = item.title,
                color = contentColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

        }

//    }

}