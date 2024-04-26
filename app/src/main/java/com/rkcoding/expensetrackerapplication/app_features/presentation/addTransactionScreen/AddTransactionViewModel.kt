package com.rkcoding.expensetrackerapplication.app_features.presentation.addTransactionScreen

import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.model.Transaction
import com.rkcoding.expensetrackerapplication.app_features.domain.repository.FirebaseTransactionRepository
import com.rkcoding.expensetrackerapplication.core.UiEvent
import com.rkcoding.expensetrackerapplication.core.navigation.Screen
import com.rkcoding.expensetrackerapplication.utils.Account
import com.rkcoding.expensetrackerapplication.utils.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val repository: FirebaseTransactionRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val transactionId = savedStateHandle.get<String>("trxId") ?: ""
    private val transactionType = savedStateHandle.get<Int>("tag") ?: 0

    private val _state = MutableStateFlow(AddTransactionState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        fetchTransaction()

    }

    fun onEvent(event: AddTransactionEvent){
        when(event){
            AddTransactionEvent.AddTransactionButtonClick -> addTransaction()

            AddTransactionEvent.CancelTransactionButtonClick -> cancelTransaction()

            is AddTransactionEvent.OnAccountTypeChange -> {
                _state.update {
                    it.copy(
                        accountType = event.account
                    )
                }
            }

            is AddTransactionEvent.OnCategoryChange -> {
                _state.update {
                    it.copy(
                        category = event.category
                    )
                }
            }

            is AddTransactionEvent.OnTransactionAmountChane -> {
                _state.update {
                    it.copy(
                        transactionAmount = event.transactionAmount
                    )
                }
            }

            is AddTransactionEvent.OnTransactionEntryDate -> {
                _state.update {
                    it.copy(
                        entryDate = event.entryDate
                    )
                }
            }

            is AddTransactionEvent.OnTransactionTitleChange -> {
                _state.update {
                    it.copy(
                        transactionTitle = event.title
                    )
                }
            }

        }
    }

    private fun cancelTransaction() {
        viewModelScope.launch {
            _uiEvent.send(
                UiEvent.NavigateTo(Screen.HomeScreen.route)
            )
            _uiEvent.send(
                UiEvent.ShowSnackBar(
                    message = "Transaction Cancelled",
                    duration = SnackbarDuration.Short
                )
            )
        }
    }

    private fun addTransaction() {
        viewModelScope.launch {
            try {

                repository.addTransaction(
                    Transaction(
                        transactionId = _state.value.transactionId,
                        transactionTitle = _state.value.transactionTitle,
//                        date = _state.value.date,
                        entryDate = _state.value.entryDate,
                        accountType = _state.value.accountType.title,
                        transactionAmount = _state.value.transactionAmount,
                        category = _state.value.category.toString(),
                        transactionType = transactionType.toString()
                    )
                )

                _uiEvent.send(
                    UiEvent.NavigateTo(Screen.HomeScreen.route)
                )
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        message = "Transaction Added Successfully",
                        duration = SnackbarDuration.Short
                    )
                )

            }catch (e: Exception){
                e.printStackTrace()
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        message = "Transaction can't be Added : ${e.message}",
                        duration = SnackbarDuration.Short
                    )
                )
            }
        }
    }


    private fun fetchTransaction(){
        viewModelScope.launch {
            repository.getTransactionById(transactionId)?.let { transaction ->
                _state.update {
                    it.copy(
                        transactionId = transaction.transactionId,
                        transactionTitle = transaction.transactionTitle,
//                        date = transaction.date,
                        entryDate = transaction.entryDate,
                        accountType = Account.valueOf(transaction.accountType),
                        transactionAmount = transaction.transactionAmount,
                        category = Category.valueOf(transaction.category),
                        transactionType = transaction.transactionType
                    )
                }
            }
        }
    }

}