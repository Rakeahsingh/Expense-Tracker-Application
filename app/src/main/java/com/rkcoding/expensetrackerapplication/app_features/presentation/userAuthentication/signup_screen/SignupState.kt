package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.signup_screen

import android.net.Uri

data class SignupState(
    val userProfileImage: Uri? = null,
    val userName: String = "",
    val userEmail: String = "",
    val userPassword: String = "",
    val userConfirmPassword: String = "",
    val isLoading: Boolean = false,
    val isSignupSuccess: Boolean = false,
    val isErrorMessage: String? = null
)
