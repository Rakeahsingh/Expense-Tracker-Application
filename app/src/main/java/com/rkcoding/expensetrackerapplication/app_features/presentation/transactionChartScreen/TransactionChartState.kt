package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.utils.TransactionType

data class TransactionChartState(
    val transactionTab: TransactionType = TransactionType.INCOME,
    val transaction: List<Transaction> = emptyList()
)
