package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import com.rkcoding.expensetrackerapplication.utils.Category
import com.rkcoding.expensetrackerapplication.utils.TransactionType

class GetIncomeTransactionByCategory(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(category: Category): List<Transaction>{
        val transaction = repository.getTransaction()

        val transactionAmount = transaction.filter {
            it.transactionType == TransactionType.INCOME.value.toString()
                    && it.category.uppercase() == category.title.uppercase()
        }.sumOf { it.transactionAmount }

        return transaction.filter {
//            it.transactionType == TransactionType.INCOME.value.toString()
//                    && it.category.uppercase() == category.title.uppercase()
            it.transactionAmount == transactionAmount
        }

    }

}