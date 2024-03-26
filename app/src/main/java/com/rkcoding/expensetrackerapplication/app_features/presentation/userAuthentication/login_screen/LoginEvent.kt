package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen

sealed class LoginEvent {

    data class OnEmailValueChange(val email: String): LoginEvent()
    data class OnPasswordValueChange(val password: String): LoginEvent()

    data object LogInButtonClick: LoginEvent()

    data object LogInWithGoogleButtonClick: LoginEvent()
}