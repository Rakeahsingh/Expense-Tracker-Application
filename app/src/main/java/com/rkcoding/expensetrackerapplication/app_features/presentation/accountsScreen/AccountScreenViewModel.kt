package com.rkcoding.expensetrackerapplication.app_features.presentation.accountsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.use_case.GetAccountDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountScreenViewModel @Inject constructor(
    private val useCase: GetAccountDetails
): ViewModel() {

    private val _state = MutableStateFlow(AccountScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val account = useCase.invoke()
            _state.update {
                it.copy(
                    accounts = account
                )
            }
        }

    }

}