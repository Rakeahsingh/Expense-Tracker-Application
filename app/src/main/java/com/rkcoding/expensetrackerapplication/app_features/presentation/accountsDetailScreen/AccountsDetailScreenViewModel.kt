package com.rkcoding.expensetrackerapplication.app_features.presentation.accountsDetailScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.use_case.GetUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountsDetailScreenViewModel @Inject constructor(
    private val useCase: GetUseCases
): ViewModel() {

    private val _state = MutableStateFlow(AccountsDetailScreenState())
    val state = _state.asStateFlow()


    fun getTransactionByAccount(accountType: String){
        viewModelScope.launch {
           val transactions = useCase.transactionByAccountUseCase.invoke(accountType)

           _state.update {
               it.copy(
                   transaction = transactions
               )
           }
        }
    }

}