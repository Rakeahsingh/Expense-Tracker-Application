package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository

class GetTransactionByAccountUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(accountType: String): List<Transaction>{
        val transactions = repository.getTransaction()

        return transactions.filter {
            accountType == it.accountType
        }

    }

}