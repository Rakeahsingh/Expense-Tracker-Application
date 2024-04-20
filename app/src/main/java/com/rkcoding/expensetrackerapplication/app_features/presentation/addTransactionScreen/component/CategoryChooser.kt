package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.AddTransactionEvent
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.AddTransactionViewModel
import com.rkcoding.expensetrackerapplication.utils.Category


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoryChooser(
    categories: List<Category> = Category.entries,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

//    var selectedCategory by remember {
//        mutableStateOf(Category.FOOD_DRINK)
//    }

    FlowRow(
       maxItemsInEachRow = 3,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(
            vertical = 8.dp,
            horizontal = 8.dp
        )
    ) {

        categories.forEach { category ->
            CategoryTag(
                category = category,
                isSelected = category == state.category,
                onCategorySelected = {
                    viewModel.onEvent(AddTransactionEvent.OnCategoryChange(it))
                }
            )
        }

    }

}

@Composable
fun CategoryTag(
    category: Category,
    isSelected: Boolean,
    onCategorySelected: (Category) -> Unit,
    cornerRadius: Dp = 12.dp
) {


    TextButton(
        onClick = { onCategorySelected(category) },
        shape = RoundedCornerShape(cornerRadius),
        contentPadding = PaddingValues(
            vertical = 4.dp,
            horizontal = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) category.iconBgColor.copy(alpha = 0.95f)
                             else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            contentColor = if (isSelected) category.iconColor
                             else MaterialTheme.colorScheme.surface
        )
    ) {

        Icon(
            painter = painterResource(
                id = if (isSelected) category.icon
                      else R.drawable.add_cart
            ),
            contentDescription = category.title
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = category.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )

    }

}