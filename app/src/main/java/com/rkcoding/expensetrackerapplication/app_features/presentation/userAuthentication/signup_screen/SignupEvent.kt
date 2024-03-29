package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.signup_screen

import com.rkcoding.expensetrackerapplication.app_features.domain.model.UserInfo

sealed class SignupEvent {

    data class OnNameValueChange(val name: String): SignupEvent()
    data class OnEmailValueChange(val email: String): SignupEvent()
    data class OnPasswordValueChange(val password: String): SignupEvent()
    data class OnConfirmPasswordValueChange(val confirmPassword: String): SignupEvent()

    data class OnGoogleButtonClick(val result: UserInfo): SignupEvent()

    data object OnSignupButtonClick: SignupEvent()


}