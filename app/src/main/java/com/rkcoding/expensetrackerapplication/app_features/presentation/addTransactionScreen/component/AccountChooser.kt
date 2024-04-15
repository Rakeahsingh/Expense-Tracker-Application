package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rkcoding.expensetrackerapplication.R
import com.rkcoding.expensetrackerapplication.ui.theme.GreenAlpha700
import com.rkcoding.expensetrackerapplication.utils.Account


@Composable
fun AccountChooser(
    accounts: Array<Account> = Account.entries.toTypedArray()
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

       accounts.forEach { account ->
           AccountTag(account = account)
       }

    }

}

@Composable
fun AccountTag(
    account: Account,
    cornerRadius: Dp = 12.dp
) {

    val selectedAccount by remember {
        mutableStateOf(Account.CASH)
    }

    TextButton(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(cornerRadius),
        contentPadding = PaddingValues(
            vertical = 4.dp,
            horizontal = 8.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (selectedAccount == account) GreenAlpha700.copy(alpha = 0.5f)
                              else Color.Transparent,
            contentColor = if (selectedAccount == account) Color.White
                            else MaterialTheme.colorScheme.primary
        )
    ) {

        Icon(
            painter = painterResource(id = if (selectedAccount == account) R.drawable.checked
                                            else account.iconRes
            )
            ,
            contentDescription = account.title
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = account.title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall
        )

    }

}