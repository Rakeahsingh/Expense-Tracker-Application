package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen

import com.rkcoding.expensetrackerapplication.utils.Account
import com.rkcoding.expensetrackerapplication.utils.Category
import java.util.Date

data class AddTransactionState(
    val transactionId: String = Date().time.toString(),
    val transactionTitle: String = "",
    val date: String = "",
    val entryDate: String = "",
    val accountType: Account = Account.CASH,
    val transactionAmount: Double = 0.00,
    val category: Category = Category.FOOD_DRINK,
    val transactionType: String = ""
)
