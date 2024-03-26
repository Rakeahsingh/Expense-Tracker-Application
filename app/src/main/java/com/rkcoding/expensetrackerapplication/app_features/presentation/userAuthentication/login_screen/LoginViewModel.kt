package com.rkcoding.expensetrackerapplication.app_features.presentation.userAuthentication.login_screen

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.UserAuthRepository
import com.rkcoding.expensetrackerapplication.core.UiEvent
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

            LoginEvent.LogInWithGoogleButtonClick -> TODO()

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
                    .onSuccess {

                        _state.update { it.copy(isLoading = false) }

                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "login Success...",
                                duration = SnackbarDuration.Short
                            )
                        )
                        _uiEvent.send(
                            UiEvent.NavigateTo
                        )
                    }
                    .onFailure { exception ->

                        _state.update { it.copy(isLoading = false) }

                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = "login Failed...${exception.message}",
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