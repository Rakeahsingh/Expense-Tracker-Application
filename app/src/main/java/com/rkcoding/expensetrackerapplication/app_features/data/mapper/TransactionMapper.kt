package com.rkcoding.expensetrackerapplication.app_features.data.mapper

import com.rkcoding.expensetrackerapplication.app_features.data.local.entity.TransactionEntity
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction

fun Transaction.toTransactionEntity(): TransactionEntity{
    return TransactionEntity(
        id = id,
        date = date,
        entryDate = entryDate,
        transactionTitle = transactionTitle,
        transactionType = transactionType,
        transactionAmount = transactionAmount,
        accountType = accountType,
        category = category
    )
}


fun TransactionEntity.toTransaction(): Transaction{
    return Transaction(
        id = id,
        date = date,
        entryDate = entryDate,
        transactionTitle = transactionTitle,
        transactionType = transactionType,
        transactionAmount = transactionAmount,
        accountType = accountType,
        category = category
    )
}