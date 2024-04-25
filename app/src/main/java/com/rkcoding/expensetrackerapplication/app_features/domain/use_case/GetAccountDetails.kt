package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import android.util.Log
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Account
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository

class GetAccountDetails(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(){
        Log.d("TotalAmount", "invoke: total Amount ")
        val transaction = repository.getTransaction().filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CASH.title
        }
       val totalAmount =  transaction.map {
            it.transactionAmount
        }.sum()

        Log.d("TotalAmount", "invoke: total Amount $totalAmount ")

    }

}