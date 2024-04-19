package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen

import com.rkcoding.expensetrackerapplication.utils.Account
import com.rkcoding.expensetrackerapplication.utils.Category

sealed class AddTransactionEvent {

    data class OnTransactionTitleChange(val title: String): AddTransactionEvent()

    data class OnAccountTypeChange(val account: Account): AddTransactionEvent()

    data class OnTransactionEntryDate(val entryDate: String): AddTransactionEvent()
    data class OnTransactionAmountChane(val transactionAmount: Double): AddTransactionEvent()

    data class OnCategoryChange(val category: Category): AddTransactionEvent()

    data object AddTransactionButtonClick: AddTransactionEvent()

    data object CancelTransactionButtonClick: AddTransactionEvent()

}