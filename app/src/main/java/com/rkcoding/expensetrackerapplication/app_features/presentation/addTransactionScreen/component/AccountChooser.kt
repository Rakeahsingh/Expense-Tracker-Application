package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.AddTransactionEvent
import com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.AddTransactionViewModel
import com.rkcoding.expensetrackerapplication.ui.theme.GreenAlpha700
import com.rkcoding.expensetrackerapplication.utils.Account


@Composable
fun AccountChooser(
    accounts: List<Account> = Account.entries,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

//    var selectedAccount by remember {
//        mutableStateOf(Account.CASH)
//    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

       accounts.forEach { account ->
           AccountTag(
               account = account,
               isSelected = account == state.accountType,
               onAccountSelected = {
                   viewModel.onEvent(AddTransactionEvent.OnAccountTypeChange(it))
               }
           )
       }

    }

}

@Composable
fun AccountTag(
    account: Account,
    isSelected: Boolean,
    onAccountSelected: (Account) -> Unit,
    cornerRadius: Dp = 12.dp
) {



    TextButton(
        onClick = { onAccountSelected(account) },
        shape = RoundedCornerShape(cornerRadius),
        contentPadding = PaddingValues(
            vertical = 4.dp,
            horizontal = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) GreenAlpha700.copy(alpha = 0.5f)
                              else Color.Transparent,
            contentColor = if (isSelected) Color.White
                            else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )
    ) {

        Icon(
            painter = painterResource(id = if (isSelected) R.drawable.checked
                                            else account.iconRes
            ),
            contentDescription = account.title,
            modifier = Modifier.size(42.dp),
            tint = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = account.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )

    }

}