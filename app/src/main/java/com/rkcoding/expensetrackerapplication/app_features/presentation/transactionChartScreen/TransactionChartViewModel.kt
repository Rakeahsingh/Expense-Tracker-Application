package com.rkcoding.expensetrackerapplication.app_features.presentation.transactionChartScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class TransactionChartViewModel @Inject constructor(

): ViewModel() {

    private val _state = MutableStateFlow(TransactionChartState())
    val state = _state.asStateFlow()

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

}