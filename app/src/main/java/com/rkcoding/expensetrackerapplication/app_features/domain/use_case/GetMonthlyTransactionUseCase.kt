package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

class GetMonthlyTransactionUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(yearOfMonth: YearMonth): List<Transaction>{
        val transactions = repository.getTransaction()

        val filteredTransactions = transactions.filter { transaction ->
            val entryYearMonth = formatTimestampToYearMonth(transaction.entryDate)
            println("Entry Date for month : $entryYearMonth, Current Date: $entryYearMonth")
            entryYearMonth == yearOfMonth
        }

        return filteredTransactions
    }

    private fun formatTimestampToYearMonth(timestampString: String): YearMonth {
        val timestamp = timestampString.toLong()

        val date = Date(timestamp)

        val calendar = Calendar.getInstance()
        calendar.time = date

        // Extract the year and month from the calendar
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1 // Note: Calendar months are zero-based

        return YearMonth.of(year, month)

    }

}