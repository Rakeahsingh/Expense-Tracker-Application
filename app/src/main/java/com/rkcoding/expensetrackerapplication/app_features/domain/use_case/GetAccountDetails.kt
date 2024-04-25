package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import android.util.Log
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Account
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository

class GetAccountDetails(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(): List<Account>{
        Log.d("TotalAmount", "invoke: total Amount ")
        val transactions = repository.getTransaction()

        // Filter transactions for each account type and calculate income and expense
        val cashIncome = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CASH.title
        }.sumOf { it.transactionAmount }
        val cashExpense = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CASH.title
        }.sumOf { it.transactionAmount }

        val bankIncome = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.BANK.title
        }.sumOf { it.transactionAmount }
        val bankExpense = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.BANK.title
        }.sumOf { it.transactionAmount }

        val cardIncome = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CARD.title
        }.sumOf { it.transactionAmount }
        val cardExpense = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CARD.title
        }.sumOf { it.transactionAmount }


        //total amounts
        val totalCashAmount = cashIncome - cashExpense
        val totalBankAmount = bankIncome - bankExpense
        val totalCardAmount = cardIncome - cardExpense


        Log.d("TotalAmount", "invoke: total Amount of accounts $totalCashAmount , $totalBankAmount, $totalCardAmount ")

        val cashAccount = Account(
            accountId = "",
            accountName = com.rkcoding.expensetrackerapplication.utils.Account.CASH.title,
            totalAmount = totalCashAmount,
            income = cashIncome,
            expense = cashExpense
        )

        val bankAccount = Account(
            accountId = "",
            accountName = com.rkcoding.expensetrackerapplication.utils.Account.BANK.title,
            totalAmount = totalBankAmount,
            income = bankIncome,
            expense = bankExpense
        )

        val cardAccount = Account(
            accountId = "",
            accountName = com.rkcoding.expensetrackerapplication.utils.Account.CARD.title,
            totalAmount = totalCardAmount,
            income = cardIncome,
            expense = cardExpense
        )

        return listOf(cashAccount, bankAccount, cardAccount)

    }

}