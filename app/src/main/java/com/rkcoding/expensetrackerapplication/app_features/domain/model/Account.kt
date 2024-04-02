package com.rkcoding.expensetrackerapplication.app_features.domain.model

data class Account (
    val accountName: String,
    val totalAmount: Double,
    val income: Double,
    val expense: Double
)