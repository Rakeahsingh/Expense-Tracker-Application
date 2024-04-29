package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component.TransactionItem
import com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen.component.BarChartItem
import com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen.component.TransactionTabButton

@Composable
fun TransactionChartScreen(
    viewModel: TransactionChartViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    if (state.transactionTab.value == 0){
        viewModel.fetchIncomeTransaction()
    }else{
        viewModel.fetchExpenseTransaction()
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(8.dp))

        // transaction tab button
        TransactionTabButton()

        // bar chart item
        BarChartItem()

        // transaction list
        LazyColumn {
            items(state.transaction){ transaction ->
                TransactionItem(transaction = transaction)
            }
        }

    }

}