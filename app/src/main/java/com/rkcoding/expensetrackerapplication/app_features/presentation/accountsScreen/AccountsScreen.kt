package com.rkcoding.expensetrackerapplication.app_features.presentation.accountsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rkcoding.expensetrackerapplication.app_features.presentation.accountsScreen.component.AccountItem

@Composable
fun AccountsScreen(
    navController: NavController,
    viewModel: AccountScreenViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()

    ) {

        Text(
            text = "Accounts",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.W700),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        LazyColumn {
            items(state.accounts){ account ->
                AccountItem(
                    account = account,
                    onAccountItemClick = {  }
                )
            }
        }

    }

}