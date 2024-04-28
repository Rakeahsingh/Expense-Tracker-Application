package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rkcoding.expensetrackerapplication.app_features.domain.use_case.GetUseCases
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

    init {
        fetchTransaction()
    }

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
            when(_state.value.transactionTab){

                TransactionType.INCOME -> {
                    val transaction = useCases.incomeTransaction.invoke()
                    _state.update {
                        it.copy(
                            transaction = transaction
                        )
                    }
                }


                TransactionType.EXPENSE -> {
                    val transaction = useCases.expenseTransaction.invoke()
                    _state.update {
                        it.copy(
                            transaction = transaction
                        )
                    }
                }

            }
        }
    }

}