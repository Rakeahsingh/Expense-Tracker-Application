package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.signup_screen

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.UserAuthRepository
import com.rkcoding.expensetrackerapplication.core.UiEvent
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repository: UserAuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(SignupState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SignupEvent){
        when(event){

            is SignupEvent.OnConfirmPasswordValueChange -> {
                _state.update { it.copy(
                    userConfirmPassword = event.confirmPassword
                ) }
            }

            is SignupEvent.OnEmailValueChange -> {
                _state.update { it.copy(
                    userEmail = event.email
                ) }
            }

            is SignupEvent.OnNameValueChange -> {
                _state.update { it.copy(
                    userName = event.name
                ) }
            }

            is SignupEvent.OnPasswordValueChange -> {
                _state.update { it.copy(
                    userPassword = event.password
                ) }
            }

            SignupEvent.OnSignupButtonClick -> {
                if (_state.value.userName.isNotBlank()){
                    signup()
                }
            }

            is SignupEvent.OnGoogleButtonClick -> {
                viewModelScope.launch {
                    try {

                        _state.update { it.copy(
                            isSignupSuccess = event.result.data != null,
                            isErrorMessage = event.result.errorMessage
                        ) }

                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "Google SignIn Successfully...",
                                duration = SnackbarDuration.Short
                            )
                        )

                        _uiEvent.send(
                            UiEvent.NavigateTo(Screen.HomeScreen.route)
                        )

                    }catch (e: Exception){
                        e.printStackTrace()
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "Google SignIn Failed... ${e.message}",
                                duration = SnackbarDuration.Long
                            )
                        )
                    }
                }
            }

        }
    }

    private fun signup() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {
                if (_state.value.userPassword != _state.value.userConfirmPassword){
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            message = "password & confirm password are not same",
                            duration = SnackbarDuration.Short
                        )
                    )
                    return@launch
                }

                repository.registerUserWithEmailPassword(
                    name = _state.value.userName,
                    email = _state.value.userEmail,
                    password = _state.value.userPassword,
                    profileImage = _state.value.userProfileImage
                )

                    .onSuccess { success ->
                        _state.update { it.copy(isLoading = false) }

                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "User Register Successfully... $success",
                                duration = SnackbarDuration.Short
                            )
                        )

                        _uiEvent.send(
                            UiEvent.NavigateTo(Screen.LogInScreen.route)
                        )

                    }
                    .onFailure { exception ->
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "User Register Failed... $exception",
                                duration = SnackbarDuration.Short
                            )
                        )
                    }

            }catch (e: Exception){
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        message = "Signup failed... ${e.message}",
                        duration = SnackbarDuration.Long
                    )
                )
            }

        }
    }

}