package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import android.util.Log
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import java.time.LocalDate
import java.util.Calendar

class GetTodayTransactionUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(): List<Transaction>{
        val transaction = repository.getTransaction()
        val date = Calendar.getInstance().time

        Log.d("current date", "invoke current date is : $date")

        return transaction.filter {
            it.entryDate == date.toString()
        }
    }

}