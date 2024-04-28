package com.rkcoding.expensetrackerapplication.app_features.presentation.homeScreen

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.utils.TabButton

data class HomeScreenState(
    val totalBalance: Int = 0,
    val totalIncome: Int = 0,
    val totalExpense: Int = 0,
    val tabButton: TabButton = TabButton.TODAY,
    val transaction: List<Transaction> = emptyList(),
    val isLoading: Boolean = false
)
