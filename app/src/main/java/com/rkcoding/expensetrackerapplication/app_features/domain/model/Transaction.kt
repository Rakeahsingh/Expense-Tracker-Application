package com.rkcoding.expensetrackerapplication.app_features.domain.model



data class Transaction(
    val transactionId: String,
    val transactionTitle: String,
//    val date: String,
    val entryDate: String,
    val accountType: String,
    val transactionAmount: Double,
    val category: String,
    val transactionType: String
)
