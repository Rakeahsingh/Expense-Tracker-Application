package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import java.time.LocalDate

class GetTodayTransactionUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(): List<Transaction>{
        val transaction = repository.getTransaction()
        val date = LocalDate.now()

        return transaction.filter {
            it.entryDate == date.toString()
        }
    }

}