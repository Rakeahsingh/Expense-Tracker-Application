package com.rkcoding.expensetrackerapplication.app_features.presentation.accountsDetailScreen

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction

data class AccountsDetailScreenState(
    val transaction: List<Transaction> = emptyList()
)
