package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class GetMonthlyTransactionUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(yearOfMonth: YearMonth): List<Transaction>{
        val transaction = repository.getTransaction()

        return transaction.filter {
            val entryDate = LocalDate.parse(it.entryDate, DateTimeFormatter.ISO_DATE)

            val transactionYearMonth = YearMonth.from(entryDate)

            transactionYearMonth == yearOfMonth
        }
    }

}