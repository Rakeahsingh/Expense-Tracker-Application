package com.rkcoding.expensetrackerapplication.app_features.presentation.accountsDetailScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rkcoding.expensetrackerapplication.app_features.presentation.component.PlaceHolder
import com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen.component.TransactionItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsDetailScreen(
    accountName: String?,
    navController: NavController,
    viewModel: AccountsDetailScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    if (accountName != null){
        viewModel.getTransactionByAccount(accountName)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Transaction")
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                       Icon(
                           imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                           contentDescription = "Arrow back icon"
                       )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {

            if (state.transaction.isEmpty()){
                PlaceHolder()
            }

            Text(
                text = accountName!!,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.W700,
                    fontSize = 28.sp
                ),
                modifier = Modifier.padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(state.transaction){ transaction ->
                    TransactionItem(transaction = transaction)
                }
            }


        }
    }

}