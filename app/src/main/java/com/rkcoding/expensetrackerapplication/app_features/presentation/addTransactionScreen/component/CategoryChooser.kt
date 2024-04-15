package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.utils.Category


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryChooser(
    categories: Array<Category> = Category.entries.toTypedArray()
) {

    FlowRow(
       maxItemsInEachRow = 3,
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 8.dp
        )
    ) {

        categories.forEach { category ->
            CategoryTag(category = category)
        }

    }

}

@Composable
fun CategoryTag(
    category: Category,
    cornerRadius: Dp = 12.dp
) {

    val selectedCategory by remember {
        mutableStateOf(Category.FOOD_DRINK)
    }

    TextButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(cornerRadius),
        contentPadding = PaddingValues(
            vertical = 4.dp,
            horizontal = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selectedCategory == category) category.iconBgColor.copy(alpha = 0.95f)
                             else MaterialTheme.colorScheme.surface,
            contentColor = if (selectedCategory == category) category.iconColor
                             else MaterialTheme.colorScheme.surface
        )
    ) {

        Icon(
            painter = painterResource(
                id = if (selectedCategory == category) category.icon
                      else R.drawable.add_cart
            ),
            contentDescription = category.title
        )

        Text(
            text = category.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )

    }

}