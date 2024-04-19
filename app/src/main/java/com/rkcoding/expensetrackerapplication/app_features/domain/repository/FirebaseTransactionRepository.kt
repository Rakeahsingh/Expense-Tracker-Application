package com.rkcoding.expensetrackerapplication.app_features.domain.repository

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import kotlinx.coroutines.flow.StateFlow

interface FirebaseTransactionRepository {

    val transaction: StateFlow<List<Transaction>>

    suspend fun addTransaction(transaction: Transaction)

    suspend fun getTransactionById(id: String): Transaction?

    suspend fun deleteTransaction(transactionId: String)

    suspend fun getTransaction(): List<Transaction>

    suspend fun realTimeTransactionData()

    fun stopTransactionRealTimeUpdate()

}