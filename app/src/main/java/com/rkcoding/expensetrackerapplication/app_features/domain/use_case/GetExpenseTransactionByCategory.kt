package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import com.rkcoding.expensetrackerapplication.utils.TransactionType

class GetExpenseTransactionByCategory(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(category: String): List<Transaction>{
        val transaction = repository.getTransaction()

        return transaction.filter {
            it.transactionType == TransactionType.EXPENSE.value.toString()
                    && it.category == category
        }

    }

}