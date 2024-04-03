package com.rkcoding.expensetrackerapplication.app_features.data.mapper

import com.rkcoding.expensetrackerapplication.app_features.data.local.entity.AccountEntity
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Account

fun Account.toAccountEntity(): AccountEntity{
    return AccountEntity(
        id = id,
        accountsType = accountName,
        income = income,
        expense =  expense,
        totalAmount = totalAmount
    )
}


fun AccountEntity.toAccount(): Account{
    return Account(
        id = id,
        accountName = accountsType,
        totalAmount = totalAmount,
        income = income,
        expense = expense
    )
}