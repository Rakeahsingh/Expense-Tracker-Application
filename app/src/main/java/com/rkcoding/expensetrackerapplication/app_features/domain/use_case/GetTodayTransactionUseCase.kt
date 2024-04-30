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
import java.util.Locale

class GetTodayTransactionUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(): List<Transaction>{
        val transaction = repository.getTransaction()

        val date = formatDate(Calendar.getInstance().time.toString())
        Log.d("current date", "invoke current date is : $date")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)
        Log.d("current date", "invoke current date is : $dateFormat")

        return transaction.filter {
            it.entryDate == date
        }
    }

    fun formatDate(timestampString: String): String {
        // Parse the timestamp string to a Long value
        val timestamp = timestampString.toLong()

        // Convert the timestamp to LocalDateTime
        val localDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(timestamp),
            ZoneId.systemDefault()
        )

        // Format the LocalDateTime into the desired date format
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return localDateTime.format(formatter)
    }

}