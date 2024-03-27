package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen

import android.util.Log
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
class LoginViewModel @Inject constructor(
    private val repository: UserAuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent){
        when(event){

            LoginEvent.LogInButtonClick -> logInWithEmail()

            is LoginEvent.OnEmailValueChange -> {
                _state.update {
                    it.copy(
                        userEmail = event.email
                    )
                }
            }

            is LoginEvent.OnPasswordValueChange -> {
                _state.update {
                    it.copy(
                        userPassword = event.password
                    )
                }
            }

            is LoginEvent.GoogleLoginButtonClick -> {
                viewModelScope.launch {
                    try {

                        _state.update { it.copy(
                            logInSuccess = event.result.data != null,
                            logInError = event.result.errorMessage
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
                                message = "Google SignIn Failed...",
                                duration = SnackbarDuration.Long
                            )
                        )
                    }
                }
            }
        }
    }


    private fun logInWithEmail() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            try {

                repository.logInUserWithEmailPassword(
                    email = _state.value.userEmail,
                    password = _state.value.userPassword
                )
                    .onSuccess { success ->
                        Log.d("Login Success", "logInWithEmail: $success")

                        _state.update { it.copy(isLoading = false) }

                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "login Success...",
                                duration = SnackbarDuration.Short
                            )
                        )
                        _uiEvent.send(
                            UiEvent.NavigateTo(Screen.HomeScreen.route)
                        )
                    }
                    .onFailure { exception ->

                        _state.update { it.copy(isLoading = false) }

                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "login Failed with error...$exception",
                                duration = SnackbarDuration.Long
                            )
                        )
                    }


            }catch (e: Exception){
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        message = "login Failed...${e.message}",
                        duration = SnackbarDuration.Long
                    )
                )
            }

        }
    }


}