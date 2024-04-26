package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen

import com.rkcoding.expensetrackerapplication.utils.TransactionType

sealed class TransactionChartEvent {

    data class OnTabButtonChange(val tabButton: TransactionType): TransactionChartEvent()

}