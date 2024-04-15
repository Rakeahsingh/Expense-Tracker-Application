package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component.AccountChooser
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component.AddTransactionTabButton
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component.CategoryChooser
import com.rkcoding.expensetrackerapplication.utils.TransactionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    transactionTag: Int?,
    transactionDate: String?,
    transactionPos: Int?,
    transactionStatus: Int?,
    navController: NavController
) {

    var text by remember {
        mutableStateOf("")
    }
    var amount by remember {
        mutableStateOf("")
    }
    val transactionType = TransactionType.entries[transactionTag!!]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Transaction") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back arrow icon",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            tint = Color.White,
                            contentDescription ="check icon",
                            modifier = Modifier
                                .size(40.dp)
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription ="check icon",
                            modifier = Modifier
                                .size(40.dp)
                                .background(MaterialTheme.colorScheme.primary, CircleShape)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {

            AddTransactionTabButton(
                onTabClick = { /*TODO*/ }
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                maxLines = 1,
                singleLine = true,
                label = { Text(text = "Enter Transaction Title") }
            )

            Text(
                text = if (transactionType == TransactionType.INCOME) "Add Fund"
                       else "Pay With",
                fontWeight = FontWeight.Bold
            )
            Divider()

            // Account type chooser
            AccountChooser()

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                maxLines = 1,
                singleLine = true,
                label = { Text(text = "Enter Transaction Amount") }
            )


            Text(
                text = "Choose Category",
                fontWeight = FontWeight.Bold,
            )

            Divider()

            Spacer(modifier = Modifier.height(8.dp))

            // category chooser
            CategoryChooser()

        }
    }
}