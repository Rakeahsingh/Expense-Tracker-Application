package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen

import com.rkcoding.expensetrackerapplication.app_features.domain.model.UserInfo

sealed class LoginEvent {

    data class GoogleLoginButtonClick(val result: UserInfo): LoginEvent()
    data class OnEmailValueChange(val email: String): LoginEvent()
    data class OnPasswordValueChange(val password: String): LoginEvent()

    data object LogInButtonClick: LoginEvent()

}