package com.rkcoding.expensetrackerapplication.app_features.presentation.accountsScreen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Account

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountItem(
    account: Account,
    onAccountItemClick: (String) -> Unit
) {


    Card(
        onClick = { onAccountItemClick(account.accountName) },
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 12.dp,
                start = 12.dp,
                end = 12.dp
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp)
        ) {

            Text(
                text = "Balance",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            
            Text(text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        letterSpacing = 0.4.sp
                    )
                ){
                    append("INR ")
                }

                withStyle(
                    SpanStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 28.sp,
                        letterSpacing = 0.25.sp
                    )
                ){
                    append(account.totalAmount.toString())
                }
            },
                modifier = Modifier.padding(start = 8.dp)
                )

            val color = when(account.accountName){
                com.rkcoding.expensetrackerapplication.utils.Account.CASH.title -> com.rkcoding.expensetrackerapplication.utils.Account.CASH.backgroundColor
                com.rkcoding.expensetrackerapplication.utils.Account.BANK.title -> com.rkcoding.expensetrackerapplication.utils.Account.BANK.backgroundColor
                else -> com.rkcoding.expensetrackerapplication.utils.Account.CARD.backgroundColor
            }

            Surface(
                color = color,
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    verticalArrangement = Arrangement.Center
                ) {

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    ) {

                        Icon(
                            painter = when(account.accountName) {
                                com.rkcoding.expensetrackerapplication.utils.Account.CASH.title -> painterResource(
                                    id = com.rkcoding.expensetrackerapplication.utils.Account.CASH.iconRes
                                )
                                com.rkcoding.expensetrackerapplication.utils.Account.BANK.title -> painterResource(
                                    id = com.rkcoding.expensetrackerapplication.utils.Account.BANK.iconRes
                                )
                                else -> painterResource(
                                    id = com.rkcoding.expensetrackerapplication.utils.Account.CARD.iconRes
                                )
                            },
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = account.accountName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )

                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 8.dp
                            )
                    ) {

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.4.sp,
                                        color = Color.White
                                    )
                                ){
                                    append("INR ")
                                }
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Light,
                                        fontSize = 18.sp,
                                        letterSpacing = 0.25.sp,
                                        color = Color.White
                                    )
                                ){
                                    append(account.income.toString())
                                }
                            }
                        )

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.4.sp,
                                        color = Color.White
                                    )
                                ){
                                    append("INR ")
                                }


                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.Light,
                                        fontSize = 18.sp,
                                        letterSpacing = 0.25.sp,
                                        color = Color.White
                                    )
                                ){
                                    append(account.expense.toString())
                                }
                            }
                        )

                    }


                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {

                        Text(
                            text = "INCOME",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier.padding(
                                bottom = 4.dp
                            )
                        )

                        Text(
                            text = "Expense",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier.padding(
                                bottom = 4.dp
                            )
                        )

                    }

                }

            }


        }

    }

}