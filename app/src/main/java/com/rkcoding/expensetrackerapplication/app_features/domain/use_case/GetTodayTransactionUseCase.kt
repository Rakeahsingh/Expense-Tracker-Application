package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import android.util.Log
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class GetTodayTransactionUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(): List<Transaction>{
        val transactions = repository.getTransaction()

        // Filter transactions based on date part of entryDate
        val filteredTransactions = transactions.filter { transaction ->
            val entryDate = formatTimestamp(transaction.entryDate)
            val currentDate = getCurrentDate()
            println("Entry Date: $entryDate, Current Date: $currentDate")
            entryDate == currentDate
        }

        return filteredTransactions
    }

    private fun formatTimestamp(timestampString: String): String {
        // Parse the timestamp string to a Long value
        val timestamp = timestampString.toLong()

        // Create a Date object from the timestamp
        val date = Date(timestamp)

        // Format the Date object into the desired date format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(date)
    }

    private fun getCurrentDate(): String {
        // Get the current date
        val currentDate = Date()

        // Format the current date into the desired date format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

}