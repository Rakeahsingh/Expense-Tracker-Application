package com.rkcoding.expensetrackerapplication.app_features.domain.model

import java.util.Date

data class Transaction(
    val id: Int,
    val transactionTitle: String,
    val date: Date,
    val entryDate: String,
    val accountType: String,
    val transactionAmount: Double,
    val category: String,
    val transactionType: String
)
