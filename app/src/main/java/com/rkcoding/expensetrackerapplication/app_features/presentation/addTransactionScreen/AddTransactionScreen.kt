package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component.AccountChooser
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component.CategoryChooser
import com.rkcoding.expensetrackerapplication.core.UiEvent
import com.rkcoding.expensetrackerapplication.utils.TransactionType
import kotlinx.coroutines.flow.collectLatest
import kotlin.reflect.KProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    transactionTag: Int?,
    transactionId: String?,
    transactionPos: Int?,
    transactionStatus: Int?,
    navController: NavController,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val scaffoldState = remember {
        SnackbarHostState()
    }

    val transactionType = TransactionType.entries[transactionTag!!]

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.NavigateTo -> event.route
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.showSnackbar(
                        message = event.message,
                        duration = event.duration
                    )
                }
            }
        }

    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = scaffoldState) },
        topBar = {
            CenterAlignedTopAppBar(
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
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .verticalScroll(rememberScrollState())
        ) {


            OutlinedTextField(
                value = state.transactionTitle,
                onValueChange = { viewModel.onEvent(AddTransactionEvent.OnTransactionTitleChange(it)) },
                maxLines = 1,
                singleLine = true,
                label = { Text(text = "Enter Transaction Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = if (transactionType == TransactionType.INCOME) "Add Fund"
                       else "Pay With",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Divider(modifier = Modifier.padding(horizontal = 12.dp))

            Spacer(modifier = Modifier.height(8.dp))

            // Account type chooser
            AccountChooser()

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.transactionAmount.toString(),
                onValueChange = { viewModel.onEvent(AddTransactionEvent.OnTransactionAmountChane(it.toDouble())) },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Go
                ),
                label = { Text(text = "Enter Transaction Amount") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )


            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Choose Category",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 12.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            Divider(modifier = Modifier.padding(horizontal = 12.dp))

            Spacer(modifier = Modifier.height(8.dp))

            // category chooser
            CategoryChooser()

            // Add Button
            TextButton(
                onClick = {
                     viewModel.onEvent(AddTransactionEvent.AddTransactionButtonClick)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Add Transaction",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Cancel Button
            TextButton(
                onClick = {
                    viewModel.onEvent(AddTransactionEvent.CancelTransactionButtonClick)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = "Cancel Transaction",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }
    }
}


