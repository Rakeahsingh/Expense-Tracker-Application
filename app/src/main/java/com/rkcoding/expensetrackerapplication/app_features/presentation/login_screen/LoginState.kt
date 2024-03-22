package com.rkcoding.expensetrackerapplication.app_features.presentation.login_screen

data class LoginState(
    val logInSuccess: Boolean = false,
    val logInError: String? = null,
    val isLoading: Boolean = false,
    val userEmail: String = "",
    val userPassword: String = ""
)
