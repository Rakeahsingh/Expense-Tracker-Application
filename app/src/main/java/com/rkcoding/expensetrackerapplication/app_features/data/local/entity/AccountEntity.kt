package com.rkcoding.expensetrackerapplication.app_features.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts_table")
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val accountsType: String,
    val totalAmount: Double,
    val income: Double,
    val expense: Double
)
