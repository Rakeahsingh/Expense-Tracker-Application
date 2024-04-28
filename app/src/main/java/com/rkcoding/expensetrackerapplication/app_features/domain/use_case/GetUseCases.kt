package com.rkcoding.expensetrackerapplication.app_features.domain.use_case

data class GetUseCases(
    val accountUseCase: GetAccountUseCase,
    val transactionByAccountUseCase: GetTransactionByAccountUseCase,
    val todayTransactionUseCase: GetTodayTransactionUseCase,
    val monthlyTransactionUseCase: GetMonthlyTransactionUseCase,
    val incomeTransaction: GetIncomeTransaction,
    val expenseTransaction: GetExpenseTransaction
)
