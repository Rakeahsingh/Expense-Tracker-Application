package com.rkcoding.expensetrackerapplication.app_features.presentation.accountsScreen

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Account

data class AccountScreenState(
    val accounts: List<Account> = emptyList()
)
