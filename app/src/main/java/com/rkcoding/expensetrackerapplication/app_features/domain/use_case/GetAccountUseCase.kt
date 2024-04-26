package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

import android.util.Log
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Account
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import com.rkcoding.expensetrackerapplication.utils.TransactionType

class GetAccountUseCase(
    private val repository: FirebaseTransactionRepository
) {

    suspend operator fun invoke(): List<Account>{

        val transactions = repository.getTransaction()

        // Filter transactions for each account type and calculate income and expense
        val cashIncome = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CASH.title
                    && it.transactionType == TransactionType.INCOME.value.toString()
        }.sumOf { it.transactionAmount }
        val cashExpense = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CASH.title
                    && it.transactionType == TransactionType.EXPENSE.value.toString()
        }.sumOf { it.transactionAmount }

        val bankIncome = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.BANK.title
                    && it.transactionType == TransactionType.INCOME.value.toString()
        }.sumOf { it.transactionAmount }
        val bankExpense = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.BANK.title
                    && it.transactionType == TransactionType.EXPENSE.value.toString()
        }.sumOf { it.transactionAmount }

        val cardIncome = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CARD.title
                    && it.transactionType == TransactionType.INCOME.value.toString()
        }.sumOf { it.transactionAmount }
        val cardExpense = transactions.filter {
            it.accountType == com.rkcoding.expensetrackerapplication.utils.Account.CARD.title
                    && it.transactionType == TransactionType.EXPENSE.value.toString()
        }.sumOf { it.transactionAmount }


        //total amounts
        val totalCashAmount = cashIncome - cashExpense
        val totalBankAmount = bankIncome - bankExpense
        val totalCardAmount = cardIncome - cardExpense


        Log.d("TotalAmount", "invoke: total Amount of accounts $totalCashAmount , $totalBankAmount, $totalCardAmount ")

        val cashAccount = Account(
            accountName = com.rkcoding.expensetrackerapplication.utils.Account.CASH.title,
            totalAmount = totalCashAmount,
            income = cashIncome,
            expense = cashExpense
        )

        val bankAccount = Account(
            accountName = com.rkcoding.expensetrackerapplication.utils.Account.BANK.title,
            totalAmount = totalBankAmount,
            income = bankIncome,
            expense = bankExpense
        )

        val cardAccount = Account(
            accountName = com.rkcoding.expensetrackerapplication.utils.Account.CARD.title,
            totalAmount = totalCardAmount,
            income = cardIncome,
            expense = cardExpense
        )

        return listOf(cashAccount, bankAccount, cardAccount)

    }

}