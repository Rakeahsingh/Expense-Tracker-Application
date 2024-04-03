package com.rkcoding.expensetrackerapplication.app_features.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val transactionTitle: String,
    val date: Date,
    val entryDate: String,
    val accountType: String,
    val transactionAmount: Double,
    val category: String,
    val transactionType: String
)
