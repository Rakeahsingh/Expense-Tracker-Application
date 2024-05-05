package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.use_case.GetUseCases
import com.rkcoding.expensetrackerapplication.utils.Category
import com.rkcoding.expensetrackerapplication.utils.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionChartViewModel @Inject constructor(
    private val useCases: GetUseCases
): ViewModel() {

    private val _state = MutableStateFlow(TransactionChartState())
    val state = _state.asStateFlow()

//    init {
//        fetchTransaction()
//    }

    fun onEvent(event: TransactionChartEvent){
        when(event){
            is TransactionChartEvent.OnTabButtonChange -> {
                _state.update {
                    it.copy(
                        transactionTab = event.tabButton
                    )
                }
            }
        }
    }


    private fun fetchTransaction(){
        viewModelScope.launch {
            val transactionType = _state.value.transactionTab.value.toString()
            val transaction = when(transactionType){

                TransactionType.INCOME.value.toString() -> useCases.incomeTransaction.invoke()

                TransactionType.EXPENSE.value.toString() -> useCases.expenseTransaction.invoke()

                else -> emptyList()

            }

            _state.update {
                it.copy(
                    transaction = transaction
                )
            }

        }
    }

    fun fetchIncomeTransaction(){
        viewModelScope.launch {
            val transaction = useCases.incomeTransaction.invoke()
            _state.update {
                it.copy(
                    transaction = transaction
                )
            }
        }
    }
    fun fetchExpenseTransaction(){
        viewModelScope.launch {
            val transaction = useCases.expenseTransaction.invoke()
            _state.update {
                it.copy(
                    transaction = transaction
                )
            }
        }
    }

    fun fetchIncomeTransactionByCategory(category: Category){
        viewModelScope.launch {

            val transaction = useCases.incomeTransactionByCategory.invoke(category)

            _state.update {
                it.copy(
                    transaction = transaction,
                )
            }

        }
    }

}