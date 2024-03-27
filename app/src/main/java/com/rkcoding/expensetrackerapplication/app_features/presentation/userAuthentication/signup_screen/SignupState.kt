package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.signup_screen

data class SignupState(
    val userProfileImage: String? = null,
    val userName: String = "",
    val userEmail: String = "",
    val userPassword: String = "",
    val userConfirmPassword: String = "",
    val isLoading: Boolean = false
)
