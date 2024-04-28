package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import com.rkcoding.expensetrackerapplication.utils.TransactionType

class GetIncomeTransaction(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(): List<Transaction>{
        val transaction = repository.getTransaction()

        return transaction.filter {
            it.transactionType == TransactionType.INCOME.value.toString()
        }
    }

}